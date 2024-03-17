package com.example.may.class12;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/06/09   10:03
 * @version: 1.0
 * @modified:
 */
public class SingletonUser {

    //私有化构造函数
    private SingletonUser() {
    }

    //定义一个静态枚举类
    static enum SingletonEnum {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private SingletonUser user;

        //私有化枚举的构造函数
        private SingletonEnum() {
            user = new SingletonUser();
        }

        public SingletonUser getInstnce() {
            return user;
        }
    }

    //对外暴露一个获取User对象的静态方法
    public static SingletonUser getInstance() {
        return SingletonEnum.INSTANCE.getInstnce();
    }

}
