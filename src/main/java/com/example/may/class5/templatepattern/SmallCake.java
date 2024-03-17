package com.example.may.class5.templatepattern;

/**
 * @description:小蛋糕
 * @author: Bruce_T
 * @date: 2022/05/27   9:42
 * @version: 1.0
 * @modified:
 */
public class SmallCake extends AbstractCake{

    private boolean flag = false;

    @Override
    protected void shape() {
        System.out.println("小蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("小蛋糕涂抹");
    }

    @Override
    protected void bake() {
        System.out.println("小蛋糕烘焙");
    }

    @Override
    protected boolean shouldApply() {
        return this.flag;
    }
}
