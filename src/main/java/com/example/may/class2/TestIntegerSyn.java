package com.example.may.class2;

/**
 * @description: 错误的加锁和原因分析
 * @author: Bruce_T
 * @date: 2022/05/22   17:07
 * @version: 1.0
 * @modified:
 */
public class TestIntegerSyn {

    public static void main(String[] args) {

        Worker worker = new Worker(1);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(worker);
            thread.start();
        }
    }

    public static class Worker implements Runnable {
        private Integer i;
        private Object object = new Object();

        public Worker(Integer i) {
            this.i = i;
        }


        @Override
        public void run() {
            synchronized (object) {
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName() + "@,i++ -->i:" + i + "---identityHashCode:" + System.identityHashCode(i));
                i++;
                System.out.println(thread.getName() + "@,i++ -->i:" + i + "---identityHashCode:" + System.identityHashCode(i));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + "@,i++ -->i:" + i + "---identityHashCode:" + System.identityHashCode(i));
            }
        }
    }

}
