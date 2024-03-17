package com.example.may.class1;

import org.apache.tomcat.util.http.ResponseUtil;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/21   18:15
 * @version: 1.0
 * @modified:
 */
public class UseJoin {

    static class Goddess implements Runnable{

        private Thread thread;

        public Goddess(Thread thread) {
            this.thread = thread;
        }


        @Override
        public void run() {
            System.out.println("Goddess 排队打饭。。");
            try {
                if(thread != null ){
                    thread.join();
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Goddess 打饭完成。。");
        }
    }

    static class GoddessBoyfriend implements Runnable{

        @Override
        public void run() {
            System.out.println("GoddessBoyfriend 排队打饭。。");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("GoddessBoyfriend 打饭完成。。");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("ready.......");
        GoddessBoyfriend goddessBoyfriend = new GoddessBoyfriend();
        Thread goddessBoyfriendThread = new Thread(goddessBoyfriend);


        Goddess goddess = new Goddess(goddessBoyfriendThread);
        Thread goddessThread = new Thread(goddess);

        goddessThread.start();
        goddessBoyfriendThread.start();

        System.out.println("主线程开始排队。。。");

        goddessThread.join();
        Thread.sleep(10);


        System.out.println(Thread.currentThread().getName() + "主线程已经打完饭。。");
    }


}
