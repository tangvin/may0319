package com.example.may.class3;

import java.util.Random;

/**
 * @description: 制造一个整形数组
 * @author: Bruce_T
 * @date: 2022/05/23   15:47
 * @version: 1.0
 * @modified:
 */
public class MakeArray {
    //数组长度
    public static final int ARRAY_LENGTH = 4000;
    //阈值
    public final static int THRESHOLD = 47;

    public static int[] makeArray() {
        //new一个随机数发生器
        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            result[i] = r.nextInt(ARRAY_LENGTH * 3);
        }
        return result;
    }
}
