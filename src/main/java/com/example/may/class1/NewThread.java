package com.example.may.class1;

/**
 * @description:类说明：新启线程的方式
 * @author: Bruce_T
 * @date: 2022/05/21   14:13
 * @version: 1.0
 * @modified:
 */
public class NewThread {

    //第一种方法：继承Thread类
    public static class UseThread extends Thread{
        @Override
        public void run() {
            super.run();
            // do my work;
            System.out.println("I am extend Thread");
        }
    }

    //第二种方法:实现Runnable接口
    public static class UseRunnable implements Runnable{

        @Override
        public void run() {
            // do my work;
            System.out.println("I am implement Runnable");
        }
    }

    public static void main(String[] args) {
        UseThread useThread = new UseThread();
        useThread.start();

        UseRunnable useRunnable = new UseRunnable();
        Thread thread = new Thread(useRunnable);
        thread.start();
    }

}
