package com.example.may.class4;

import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/24   14:34
 * @version: 1.0
 * @modified:
 */
public class SemaphoreDemo {
    static class TaskThread extends Thread {

        Semaphore semaphore;

        public TaskThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(getName() + " acquire");
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(getName() + " release ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNumber = 5;
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < threadNumber; i++) {
            new TaskThread(semaphore).start();
        }
    }



}
