package com.example.may.class4;

import com.example.may.tools.SleepTools;

import java.sql.Connection;
import java.util.Random;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/24   13:08
 * @version: 1.0
 * @modified:
 */
public class DBPoolSemaphoreTest {

    private static DBPoolSemaphore dbPoolSemaphore = new DBPoolSemaphore();

    private static class BusinessThread extends Thread{
        @Override
        public void run() {
            try {
                Random r = new Random();//让每个线程持有连接的时间不一样
                long start = System.currentTimeMillis();

                Connection connection = dbPoolSemaphore.takeConnection();
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +"_获取数据库连接共耗时【"+(System.currentTimeMillis()-start)+"】ms.");
                SleepTools.ms(100+r.nextInt(100));//模拟业务操作，线程持有连接查询数据
                System.out.println("查询数据完成，归还连接！");
                dbPoolSemaphore.returnConnection(connection);
            }catch (InterruptedException e){

            }

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
           new Thread(new BusinessThread()).start();
        }
    }
}
