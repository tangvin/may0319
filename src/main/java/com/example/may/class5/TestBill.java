package com.example.may.class5;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/25   16:03
 * @version: 1.0
 * @modified:
 */
public class TestBill extends Thread{
    @Override
    public void start() {
        System.out.println("1111111111111111");
    }

    public static void main(String[] args) {
        TestBill testBill = new TestBill();
        testBill.run();
    }
}
