package com.example.may.class6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description:共享同步工具类
 * @author: Bruce_T
 * @date: 2022/05/26   18:47
 * @version: 1.0
 * @modified:
 */
public class TrinityLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException();
            }
            setState(count);
        }


        /**
         * @param reduceCount 扣减个数
         * @return 返回小于0，表示当前线程获得同步状态失败
         * 大于0，表示当前线程获得同步状态成功
         */
        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        /**
         * @param returnCount 归还个数
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

    //为n表示允许n个线程同时获得锁
    private final Sync sync = new Sync(4);


    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }


    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
