package com.example.may.class2;

/**
 * @description: ThreadLocal的线程不安全演示
 * @author: Bruce_T
 * @date: 2022/05/22   18:25
 * @version: 1.0
 * @modified:
 */
public class ThreadLocalUnsafe implements Runnable {

    public Number number = new Number(0);

    public ThreadLocal<Number> numberThreadLocal = new ThreadLocal<Number>() {
    };

    public void run() {
        //每个线程计数加一
        number.setNum(number.getNum() + 1);
        //将其存储到ThreadLocal中
        numberThreadLocal.set(number);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出num值
        System.out.println(Thread.currentThread().getName() + "=" + numberThreadLocal.get().getNum());
    }

    private static class Number {
        public Number(int num) {
            this.num = num;
        }

        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Number [num=" + num + "]";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadLocalUnsafe()).start();
        }
    }
}
