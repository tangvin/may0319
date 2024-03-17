package com.example.may.class6;

import com.example.may.tools.SleepTools;
import org.apache.el.parser.AstMinus;
import sun.applet.Main;

import java.util.concurrent.locks.Lock;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/26   18:17
 * @version: 1.0
 * @modified:
 */
public class TestMyLock {

    public void test() {
        final Lock lock = new SelfLock();
        class Worker extends Thread {
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                try {
                    SleepTools.second(1);
                } finally {
                    lock.unlock();
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            Worker worker = new Worker();
            worker.start();
        }
        for (int i = 0; i < 10; i++) {
            SleepTools.second(1);
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock = new TestMyLock();
        testMyLock.test();
    }
}
