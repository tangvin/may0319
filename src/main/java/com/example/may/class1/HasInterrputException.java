package com.example.may.class1;

/**
 * @description: 阻塞方法中抛出InterruptedException异常后，如果需要继续中断，需要手动再中断一次
 * @author: Bruce_T
 * @date: 2022/05/21   18:02
 * @version: 1.0
 * @modified:
 */
public class HasInterrputException {

    public static class ExThread extends Thread{
        public ExThread(String name) {
            super(name);
        }

        @Override
        public void run() {
           while (!isInterrupted()){
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   System.out.println(Thread.currentThread().getName()
                           +" in InterruptedException interrupt flag is "
                           +isInterrupted());
                   //资源释放
//                   interrupt();
                   e.printStackTrace();
               }
               System.out.println(Thread.currentThread().getName()
                       + " I am extends Thread.");
           }
            System.out.println(Thread.currentThread().getName()
                    +" interrupt flag is "+isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExThread exThread = new ExThread("ExThread");
        exThread.start();
        Thread.sleep(500);
        exThread.interrupt();
    }
}
