package com.example.may.class4;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/24   12:09
 * @version: 1.0
 * @modified:
 */
public class TourGuideTask  implements Runnable{
    @Override
    public void run() {
        System.out.println("****导游分发护照签证****");
        try {
            //模拟发护照签证需要2秒
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
