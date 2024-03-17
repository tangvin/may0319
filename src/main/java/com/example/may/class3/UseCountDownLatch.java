package com.example.may.class3;

import com.example.may.tools.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 * @description: *类说明：演示CountDownLatch用法，
 * 共5个初始化子线程，6个闭锁扣除点，扣除完毕后，主线程和业务线程才能继续执行
 * @author: Bruce_T
 * @date: 2022/05/23   16:33
 * @version: 1.0
 * @modified:
 */
public class UseCountDownLatch {


    static CountDownLatch countDownLatch = new CountDownLatch(6);

    /*初始化线程*/
    private static class InitThread implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread_" + Thread.currentThread().getId()
                    + " ready init work......");
            for (int i = 0; i < 2; i++) {
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " ........continue do its work");
            }
            countDownLatch.countDown();
        }
    }

    /*业务线程等待latch的计数器为0完成*/
    private static class BusinessThread implements Runnable {

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("BusinessThread_" + Thread.currentThread().getId()
                        + " do business-----");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId() + " ready init work step 1st......");
                countDownLatch.countDown();

                System.out.println("begin step 2nd.......");
                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId() + " ready init work step 2nd......");
                countDownLatch.countDown();

            }
        }).start();

        new Thread(new BusinessThread()).start();
        for (int i = 0; i <= 3; i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }

        countDownLatch.await();
        System.out.println("Main do ites work........");
    }

}

