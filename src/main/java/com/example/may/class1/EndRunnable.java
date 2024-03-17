package com.example.may.class1;

/**
 * @description:实现接口Runnable的线程如何中断
 * @author: Bruce_T
 * @date: 2022/05/21   17:53
 * @version: 1.0
 * @modified:
 */
public class EndRunnable {

    public static class UseRunnable implements Runnable{
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("ThreadName=="+Thread.currentThread().getName()+"----implements Runnable");
                System.out.println("ThreadName=="+Thread.currentThread().getName()+"----flag=="+Thread.currentThread().isInterrupted());
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread thread = new Thread(useRunnable, "EndRunnable");
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
    }
}
