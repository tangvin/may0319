package com.example.may.class4;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/24   14:24
 * @version: 1.0
 * @modified:
 */
public class UseExchange {


    private static final Exchanger<Set<String>> exchanger = new Exchanger<Set<String>>();


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //存放数据的容器
                Set<String> setA = new HashSet<>();
                try {
                    //添加数据
                    setA.add("1");
                    setA = exchanger.exchange(setA);
                    System.out.println("setA=="+setA);
                } catch (InterruptedException e) {

                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                //存放数据的容器
                Set<String> setB = new HashSet<String>();
                try {
                    setB.add("2");
                    setB.add("3");
                    setB = exchanger.exchange(setB);//交换set
                    /*处理交换后的数据*/
                    System.out.println("setB=="+setB);
                } catch (InterruptedException e) {

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //存放数据的容器
                Set<String> setC = new HashSet<String>();
                try {
                    setC.add("4");
                    setC.add("5");
                    setC = exchanger.exchange(setC);//交换set
                    /*处理交换后的数据*/
                    System.out.println("setC=="+setC);
                } catch (InterruptedException e) {

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //存放数据的容器
                Set<String> setD = new HashSet<String>();
                try {
                    setD.add("99");
                    setD.add("88");
                    setD = exchanger.exchange(setD);//交换set
                    /*处理交换后的数据*/
                    System.out.println("setD=="+setD);
                } catch (InterruptedException e) {

                }
            }
        }).start();
    }
}
