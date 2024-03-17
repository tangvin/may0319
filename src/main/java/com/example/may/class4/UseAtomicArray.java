package com.example.may.class4;

import sun.applet.Main;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/24   21:41
 * @version: 1.0
 * @modified:
 */
public class UseAtomicArray {

    static int[] value = {1,2,3,4};
    static AtomicIntegerArray atomicIntegerArray =  new AtomicIntegerArray(value);


    public static void main(String[] args) {
        atomicIntegerArray.getAndSet(0,19999);
        System.out.println(value[0]);
        System.out.println(atomicIntegerArray.get(0));
    }
}
