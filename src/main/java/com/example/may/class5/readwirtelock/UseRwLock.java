package com.example.may.class5.readwirtelock;

import com.example.may.tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/25   14:51
 * @version: 1.0
 * @modified:
 */
public class UseRwLock implements GoodsService {

    private GoodsInfo goodsInfo;

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();//读锁
    private final Lock writeLock = lock.writeLock();//写锁


    @Override
    public GoodsInfo getNum() {
        readLock.lock();
        try {
            SleepTools.ms(5);
            return this.goodsInfo;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void setNum(int number) {
        writeLock.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        } finally {
            writeLock.unlock();
        }
    }
}
