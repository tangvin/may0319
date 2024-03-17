package com.example.may.class5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 使用Lock的范例
 * @author: Bruce_T
 * @date: 2022/05/25   11:59
 * @version: 1.0
 * @modified:
 */
public class LockCase {


    private static Lock lock = new ReentrantLock();

    private  int age = 100;

    public  int getAge() {
        return age;
    }

    public void test() {
        //不要将获取锁的过程写在 try 块中，因为如果在获取锁（自定义锁的实现） 时发生了异常，异常抛出的同时，也会导致锁无故释放
        lock.lock();
        try {
            age++;
        } finally {
            lock.unlock();
        }
    }

    public void test2() {
        //不要将获取锁的过程写在 try 块中，因为如果在获取锁（自定义锁的实现） 时发生了异常，异常抛出的同时，也会导致锁无故释放
        lock.lock();
        try {
            age--;
        } finally {
            lock.unlock();
        }
    }

    static class TestThread extends Thread {

        private LockCase lockCase;

        public TestThread(LockCase lockCase, String name) {
            super(name);
            this.lockCase = lockCase;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {//递增100
                lockCase.test2();
            }
            System.out.println(Thread.currentThread().getName() + " age =  " + lockCase.getAge());
        }
    }

    public static void main(String[] args) {
        LockCase lockCase = new LockCase();
//        TestThread endThread = new TestThread(lockCase, "EndThread");
//        endThread.start();
        for (int i = 0; i < 100; i++) {//递减100
            lockCase.test2();
        }
        System.out.println(Thread.currentThread().getName()
                + " age =  " + lockCase.getAge());
    }


}
