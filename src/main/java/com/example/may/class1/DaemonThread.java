package com.example.may.class1;

/**
 * @description: 守护线程的使用
 * @author: Bruce_T
 * @date: 2022/05/21   18:45
 * @version: 1.0
 * @modified:
 */
public class DaemonThread {


    public static class UseThread extends Thread{
        @Override
        public void run() {
            try {
                while (!isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + " I am extends Thread.");
                }
                System.out.println(Thread.currentThread().getName()
                        + " interrupt flag is " + isInterrupted());
            }finally {
                //守护线程中finally不一定起作用
                System.out.println(" .............finally");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        useThread.start();
        Thread.sleep(10);
        useThread.interrupt();



    }

}
