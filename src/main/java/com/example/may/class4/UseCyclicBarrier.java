package com.example.may.class4;

import com.example.may.tools.SleepTools;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: 类说明：演示CyclicBarrier用法,共4个子线程，他们全部完成工作后，交出自己结果，
 * 再被统一释放去做自己的事情，而交出的结果被另外的线程拿来拼接字符串
 * @author: Bruce_T
 * @date: 2022/05/24   11:43
 * @version: 1.0
 * @modified:
 */
public class UseCyclicBarrier {

    private static CyclicBarrier barrier
            = new CyclicBarrier(4, new CollectionThread());

    //存放子线程工作结果的容器
    private static ConcurrentHashMap<String, Long> resultMap
            = new ConcurrentHashMap<>();


    /*相互等待的子线程*/
    private static class SubThread implements Runnable {

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            resultMap.put(Thread.currentThread().getId() + "", id);

            try {
                Thread.sleep(1000 + id);
                System.out.println("Thread_" + id + " ....do something ");
                barrier.await();
                Thread.sleep(1000+id);
                System.out.println("等待结束，四个线程一起执行");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class CollectionThread implements Runnable{

        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Long> workResult : resultMap.entrySet()) {
                result.append("["+workResult.getValue()+"]");
            }
            System.out.println(" the result = "+ result);
            System.out.println("do other business........");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new SubThread());
            thread.start();
        }
    }


}
