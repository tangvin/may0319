package com.example.may.class3;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/23   23:27
 * @version: 1.0
 * @modified:
 */
public class DBPool2 {
    //容器，模拟线程池中的线程
    private static LinkedList<Connection> pool = new LinkedList();

    //构造方法，指定线程池的初始值
    public DBPool2(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(new SqlConnectImpl());
            }
        }
    }

    //释放连接
    public void releaseConnection2(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                //通知其他等待连接的线程
                pool.notifyAll();
            }
        }
    }

    //获取连接
    public Connection fetchConnection2(long time) throws InterruptedException {
        synchronized (pool) {
            //没有超时
            if (time <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();

            } else {
                //超时时刻
                long future = System.currentTimeMillis() + time;
                //等待时长
                long remaining = time;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    //重新计算超时时间
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }

    }


}
