package com.example.may.class2;

/**
 * @description: 演示Volatile的提供的可见性
 * @author: Bruce_T
 * @date: 2022/05/22   17:44
 * @version: 1.0
 * @modified:
 */
public class VolatileCase {

    private static volatile boolean ready;
    private static  int number;


    public static class PrintThread extends Thread {
        @Override
        public void run() {
            System.out.println("ready:" + ready);
            while (!ready) ;
            System.out.println("number :" + number);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        PrintThread printThread = new PrintThread();
        printThread.start();
//        Thread.sleep(1000);
        number = 100;
        ready = true;
        Thread.sleep(3000);
        System.out.println("main is end...");
    }

}
