package com.example.may.class12;

/**
 * 饿汉式
 * 枚举
 */
public class SingleEHan {
    private SingleEHan(){}
    private static SingleEHan singleDcl = new SingleEHan();

    public static SingleEHan getSingleDcl() {
        return singleDcl;
    }
}
