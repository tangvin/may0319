package com.example.may.class10;

import java.util.Arrays;
import java.util.Random;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/06/01   15:16
 * @version: 1.0
 * @modified:
 */
public class TestFinalData {
    private static Random random = new Random(47);

    private String id;

    public TestFinalData(String id) {
        this.id = id;
    }

    private final int valueOne = 9;

    private final static int VALUE_TWO = 99;
    private final static int VALUE_THREE = 39;

    private final int i4 = random.nextInt(20);
    static final int INT_5 = random.nextInt(20);


    private TestValue v1 = new TestValue(11);
    private static final TestValue v2 = new TestValue(22);


    private final int[] a = {1,23,3,4,45,5};

    @Override
    public String
    toString() {
        return "TestFinalData{" +
                "id='" + id + '\'' +
                ", i4=" + i4 +
                ", INT_5" + INT_5 +
                '}';
    }


    public static void main(String[] args) {
        TestFinalData fd1 = new TestFinalData("fd1");
//        fd1.valueOne ++;
        fd1.v2.i++;
        System.out.println("111-------------"+fd1);
        fd1.v1 = new TestValue(9);
        System.out.println("222-------------"+fd1.v1);
    }
}
