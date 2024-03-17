package com.example.may.class2;

/**
 * @description: 没有使用ThreadLocal
 * @author: Bruce_T
 * @date: 2022/05/22   18:02
 * @version: 1.0
 * @modified:
 */
public class NoThreadLocal {
    static Integer count = new Integer(1);

    public static class TestTask implements Runnable {

        int id;

        public TestTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            count = count + id;
            System.out.println(Thread.currentThread().getName() + ":" + count);
        }
    }

    public void StartThreadArray() {
        Thread[] runs = new Thread[3];
        //创建线程
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestTask(i));
        }
        //启动线程
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }
    }

    public static void main(String[] args) {
        NoThreadLocal noThreadLocal = new NoThreadLocal();
        noThreadLocal.StartThreadArray();
    }

}
