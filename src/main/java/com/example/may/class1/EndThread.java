package com.example.may.class1;

/**
 * @description: 如何安全中断线程
 * @author: Bruce_T
 * @date: 2022/05/21   14:27
 * @version: 1.0
 * @modified:
 */
public class EndThread {

    public static class UseThread extends Thread{

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+ ": interrupt flag--> "+ isInterrupted() );

//            while(!isInterrupted()){
                while(!Thread.interrupted()){
//                while(true){
                System.out.println("running....");
                System.out.println(threadName+"inner interrupt flag = "+isInterrupted());
            }
            System.out.println(threadName+"interrupt  flag ="+isInterrupted());

        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread endThread = new UseThread("EndThread");
        endThread.start();
        Thread.sleep(10);
        endThread.interrupt();
    }
}
