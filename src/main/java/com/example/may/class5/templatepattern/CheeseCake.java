package com.example.may.class5.templatepattern;

/**
 * @description:芝士蛋糕
 * @author: Bruce_T
 * @date: 2022/05/27   9:36
 * @version: 1.0
 * @modified:
 */
public class CheeseCake extends AbstractCake{
    @Override
    protected void shape() {
        System.out.println("芝士蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("芝士蛋糕涂抹");
    }

    @Override
    protected void bake() {
        System.out.println("芝士蛋糕烘焙");
    }
}
