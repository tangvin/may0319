package com.example.may.class5.templatepattern;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/27   9:38
 * @version: 1.0
 * @modified:
 */
public class MousseCake extends AbstractCake {
    @Override
    protected void shape() {
        System.out.println("慕斯蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("慕斯蛋糕涂抹");
    }

    @Override
    protected void bake() {
        System.out.println("慕斯蛋糕烘焙");
    }
}
