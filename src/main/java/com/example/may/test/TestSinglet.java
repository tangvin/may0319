package com.example.may.test;

import com.example.may.class12.SingleDcl;
import com.example.may.class12.SingleEHan;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/06/09   12:04
 * @version: 1.0
 * @modified:
 */
public class TestSinglet {


    public void test(){
        SingleDcl singleDcl1 = SingleDcl.getInstance();

        SingleEHan singleDcl = SingleEHan.getSingleDcl();
    }
}
