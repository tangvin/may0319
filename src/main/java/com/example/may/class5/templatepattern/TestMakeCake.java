package com.example.may.class5.templatepattern;

/**
 * @description:生产蛋糕
 * @author: Bruce_T
 * @date: 2022/05/27   9:38
 * @version: 1.0
 * @modified:
 */
public class TestMakeCake {

    public static void main(String[] args) {
        AbstractCake cheeseCake = new CheeseCake();
        cheeseCake.run();
        System.out.println("-------------------------");
        AbstractCake creamCake = new CreamCake();
        creamCake.run();
        System.out.println("-------------------------");
        AbstractCake mousseCake = new MousseCake();
        mousseCake.run();
        System.out.println("-------------------------");
        AbstractCake smallCake = new SmallCake();
        smallCake.run();
    }
}
