package com.example.may.class1;

/**
 * @description: synchronized关键字的使用方法
 * @author: Bruce_T
 * @date: 2022/05/21   19:12
 * @version: 1.0
 * @modified:
 */
public class SynTest {

    private long count = 0l;
    private Object object = new Object();


    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


    //用在同步块上面的
    private void addCount() {
        synchronized (object) {
            count++;
        }
    }

    private synchronized void addCount2(){
        count ++;
    }

    /*用在同步块上，但是锁的是当前类的对象实例*/
    public void addCount3(){
        synchronized (this){
            count++;
        }
    }


    private static class AddCount extends Thread{
        private SynTest synTest;

        public AddCount(SynTest synTest){
            this.synTest = synTest;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synTest.addCount3();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynTest synTest = new SynTest();
        //启动2个线程
        AddCount addCount1 = new AddCount(synTest);
        AddCount addCount2 = new AddCount(synTest);
        addCount1.start();
        addCount2.start();
        Thread.sleep(20);
        System.out.println(synTest.count);

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors:"+availableProcessors);
    }
}
