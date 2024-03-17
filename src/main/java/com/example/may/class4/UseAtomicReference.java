package com.example.may.class4;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/24   21:49
 * @version: 1.0
 * @modified:
 */
public class UseAtomicReference {

    static AtomicReference<UserInfo> atomicReference;

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo("HELLO", 19);
        atomicReference = new AtomicReference(userInfo);
        UserInfo updateUser = new UserInfo("WORLD", 20);
        atomicReference.compareAndSet(userInfo,updateUser);
        System.out.println(atomicReference.get());
        System.out.println(userInfo);
    }

    //定义一个实体类
    static class UserInfo {
        private volatile String name;
        private int age;
        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
