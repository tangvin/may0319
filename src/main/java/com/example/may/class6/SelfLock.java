package com.example.may.class6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description:实现我们自己独占锁,不可重入
 * @author: Bruce_T
 * @date: 2022/05/26   18:02
 * @version: 1.0
 * @modified:
 */
public class SelfLock implements Lock {

    //静态内部内，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {

        /*判断处于占用状态*/
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /*获得锁*/
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }


        /*释放锁*/
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    //仅需将操作代理到Sync上面即可
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        System.out.println(Thread.currentThread().getName() + " ready get lock");
        sync.acquire(1);
        System.out.println(Thread.currentThread().getName() + " already got lock");
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }


    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + " ready release lock");
        sync.release(1);
        System.out.println(Thread.currentThread().getName() + " already released lock");
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
