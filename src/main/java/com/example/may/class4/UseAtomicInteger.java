package com.example.may.class4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/24   21:37
 * @version: 1.0
 * @modified:
 */
public class UseAtomicInteger {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
//        int i = atomicInteger.addAndGet(10);
//        System.out.println(i);
//        boolean b = atomicInteger.compareAndSet(11, 3);
//        System.out.println(b);
//        int andSet = atomicInteger.getAndSet(100);
//        System.out.println(andSet);
//        System.out.println(atomicInteger);
//        int incrementAndGet = atomicInteger.incrementAndGet();
//        System.out.println("incrementAndGet:  "+incrementAndGet);
//        System.out.println("atomicInteger:  "+atomicInteger);

        int getAndIncrement = atomicInteger.getAndIncrement();
        System.out.println("getAndIncrement:  "+getAndIncrement);
        System.out.println("atomicInteger:  "+atomicInteger);
    }
}
