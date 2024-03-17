package com.example.may.class5.readwirtelock;

import com.example.may.tools.SleepTools;

/**
 * @description:用内置锁来实现商品服务接口
 * @author: Bruce_T
 * @date: 2022/05/25   14:37
 * @version: 1.0
 * @modified:
 */
public class UseSync implements GoodsService{

    private GoodsInfo goodsInfo;

    public UseSync(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() {
        SleepTools.ms(5);
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        SleepTools.ms(5);
        goodsInfo.changeNumber(number);
    }
}
