package com.example.may.class2;

/**
 * @description: 演示ThreadLocal的使用
 * @author: Bruce_T
 * @date: 2022/05/22   18:10
 * @version: 1.0
 * @modified:
 */
public class UseThreadLocal {

    //todo
    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };


    /**
     * 运行3个线程
     */
    public void startThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestThread(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }
    }

    /**
     * 类说明：测试线程，线程的工作是将ThreadLocal变量的值变化，并写回，看看线程之间是否会互相影响
     */
    public static class TestThread implements Runnable {
        int id;

        public TestThread(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            //todo
            Integer s = integerThreadLocal.get();
            s = s + id;
            integerThreadLocal.set(s);
            System.out.println(Thread.currentThread().getName() +":"+ integerThreadLocal.get());
            integerThreadLocal.remove();
        }
    }

    public static void main(String[] args) {
        UseThreadLocal test = new UseThreadLocal();
        test.startThreadArray();
    }
}
