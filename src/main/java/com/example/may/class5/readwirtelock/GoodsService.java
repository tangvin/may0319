package com.example.may.class5.readwirtelock;

/**
 * @description:商品的服务的接口
 * @author: Bruce_T
 * @date: 2022/05/25   14:36
 * @version: 1.0
 * @modified:
 */
public interface GoodsService {

    //获得商品的信息
    GoodsInfo getNum();

    //设置商品的数量
    void setNum(int number);
}
