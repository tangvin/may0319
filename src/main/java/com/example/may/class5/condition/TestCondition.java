package com.example.may.class5.condition;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/25   15:20
 * @version: 1.0
 * @modified:
 */
public class TestCondition {
    private static ExpressCondition express = new ExpressCondition(0, ExpressCondition.CITY);
    private static ExpressCondOneLock express2 = new ExpressCondOneLock(0, ExpressCondition.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express2.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    private static class CheckSite extends Thread {
        @Override
        public void run() {
            express2.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }
        Thread.sleep(1000);
        express2.changeKm();//快递里程变化
        express2.changeSite();//地点变化
    }


}
