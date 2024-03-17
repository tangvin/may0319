package com.example.may.class6;

import com.example.may.tools.SleepTools;

import java.util.concurrent.locks.Lock;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/26   18:32
 * @version: 1.0
 * @modified:
 */
public class TestReenterSelfLock {

    static final Lock lock = new ReenterSelfLock();

    public void reenter(int x) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":递归层级:" + x);
            int y = x - 1;
            if (y == 0) return;
            else {
                reenter(y);
            }
        } finally {
            lock.unlock();
        }
    }

    public void test() {
        class Worker extends Thread {
            public void run() {
                System.out.println("11111"+Thread.currentThread().getName());
                SleepTools.second(1);
                reenter(3);
            }
        }
        // 启动3个子线程
        for (int i = 0; i < 3; i++) {
            Worker w = new Worker();
            w.start();
        }
        // 主线程每隔1秒唤醒
        for (int i = 0; i < 100; i++) {
            SleepTools.second(1);
        }
    }

    public static void main(String[] args) {
        TestReenterSelfLock testMyLock = new TestReenterSelfLock();
        testMyLock.test();
    }
}
