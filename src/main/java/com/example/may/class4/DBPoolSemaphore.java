package com.example.may.class4;

import com.example.may.class3.SqlConnectImpl;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @description: 演示Semaphore用法，一个数据库连接池的实现
 * @author: Bruce_T
 * @date: 2022/05/24   12:58
 * @version: 1.0
 * @modified:
 */
public class DBPoolSemaphore {
    //指定连接数的最大值
    private final static int POOL_SIZE = 10;
    //两个指示器，分别表示池子还有的可用连接和已用连接
    private final Semaphore useful, useless;
    //存放数据库连接的容器
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    //初始化池
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }


    public DBPoolSemaphore() {
        this.useful = new Semaphore(10);
        this.useless = new Semaphore(0);
    }

    //归还连接
    public void returnConnection(Connection connection) throws InterruptedException {
        if (connection != null) {
            //返回正在等待许可证的线程数-->int getQueueLength()
            //返回此信号量中当前可用的许可证数--> int availablePermits()
            System.out.println("当前有" + useful.getQueueLength() + "个线程等待数据库连接!!" + "可用连接数：" + useful.availablePermits());
            //获取许可证  acquire() 表示阻塞并获取许可
            useless.acquire();
            synchronized (pool){
                pool.addLast(connection);
            }
            useful.release();
        }
    }


    //从池子拿连接
    public Connection takeConnection() throws InterruptedException {
        //获取许可证  acquire() 表示阻塞并获取许可
        useful.acquire();
        Connection connection;
        synchronized (pool){
            connection = pool.removeFirst();
        }
        //表示释放许可
        useless.release();
        return connection;
    }
}
