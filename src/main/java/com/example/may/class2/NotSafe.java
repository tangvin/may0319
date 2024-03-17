package com.example.may.class2;

/**
 * @description: volatile 不能保证数据在多个线程下同时写时的线程安全
 * @author: Bruce_T
 * @date: 2022/05/22   17:53
 * @version: 1.0
 * @modified:
 */
public class NotSafe {

    private volatile long count = 0;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    //count进行累加
    public void addCount() {
        count++;
    }

    //线程
    private static class Count extends Thread {

        private NotSafe notSafe;

        public Count(NotSafe notSafe) {
            this.notSafe = notSafe;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                notSafe.addCount();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NotSafe notSafe = new NotSafe();
        //启动两个线程
        Count count1 = new Count(notSafe);
        Count count2 = new Count(notSafe);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println(notSafe.count);
    }
}
