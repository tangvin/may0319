package com.example.may.class4;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/24   12:09
 * @version: 1.0
 * @modified:
 */
public class TourClientTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new TourGuideTask());

//        for (int i = 0; i < 3; i++) {
//            TourGuideTask task1 = new TourGuideTask();
//            new Thread(task1).start();
//        }
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new TravelTask(cyclicBarrier,"张三",1));
        executorService.execute(new TravelTask(cyclicBarrier,"六四",2));
        executorService.execute(new TravelTask(cyclicBarrier,"古额",3));

    }
}
