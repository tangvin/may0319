package com.example.may.class4;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/24   22:13
 * @version: 1.0
 * @modified:
 */
public class UseAtomicMarkableReference {


    static AtomicMarkableReference<String> atomicMarkableReference =
            new AtomicMarkableReference<String>("Tom", false);

    public static void main(String[] args) throws InterruptedException {

        //拿到当前的版本号(旧)
        String oldReference = atomicMarkableReference.getReference();
        boolean oldMarked = atomicMarkableReference.isMarked();

        System.out.println("初始化之后的值：" + oldReference);
        System.out.println("初始化之后的标记：" + oldMarked);

        System.out.println("--------------------------------------");
        String newReference = "Black";
        boolean b = atomicMarkableReference.compareAndSet(oldReference, newReference, false, true);
        if (!b) {
            System.out.println("expectedMark 不一致，无法修改Reference的值");
        }
        System.out.println("修改成功之后的Mark：" + atomicMarkableReference.isMarked());
        System.out.println("修改成功之后的值：" + atomicMarkableReference.getReference());

    }

}
