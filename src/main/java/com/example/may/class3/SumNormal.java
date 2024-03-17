package com.example.may.class3;

import com.example.may.tools.SleepTools;

/**
 * @description: 统计整形数组中所有元素的和(没有使用ForkJoin)
 * @author: Bruce_T
 * @date: 2022/05/23   15:51
 * @version: 1.0
 * @modified:
 */
public class SumNormal {

    public static void main(String[] args) {
        int count = 0;
        int[] src = MakeArray.makeArray();

        long start = System.currentTimeMillis();
        for (int i = 0; i < src.length; i++) {
            SleepTools.ms(1);
            count = count + src[i];
        }
        System.out.println("The count is " + count + " spend time:" + (System.currentTimeMillis() - start) + "ms");
    }
}
