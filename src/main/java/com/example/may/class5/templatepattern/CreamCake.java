package com.example.may.class5.templatepattern;

/**
 * @description:奶油蛋糕
 * @author: Bruce_T
 * @date: 2022/05/27   9:37
 * @version: 1.0
 * @modified:
 */
public class CreamCake extends AbstractCake{
    @Override
    protected void shape() {
        System.out.println("奶油蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("奶油蛋糕涂抹");
    }

    @Override
    protected void bake() {
        System.out.println("奶油蛋糕烘焙");
    }
}
