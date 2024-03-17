package com.example.may.class1;

/**
 * @description: StartAndRun在执行上的区别
 * @author: Bruce_T
 * @date: 2022/05/22   17:03
 * @version: 1.0
 * @modified:
 */
public class StartAndRun {

    public static class ThreadRun extends Thread{
        @Override
        public void run() {
            int i = 90;
            while(i>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
                System.out.println("I am "+Thread.currentThread().getName() +" and now the i="+i--);
            }
        }
    }


    public static void main(String[] args) {
        ThreadRun threadRun = new ThreadRun();
        threadRun.setName("ThreadRun");
        threadRun.start();
    }
}
