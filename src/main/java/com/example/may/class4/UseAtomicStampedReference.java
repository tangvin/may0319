package com.example.may.class4;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.omg.CORBA.RepositoryIdHelper;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description:演示带版本戳的原子操作类
 * @author: Bruce_T
 * @date: 2022/05/24   21:54
 * @version: 1.0
 * @modified:
 */
public class UseAtomicStampedReference {


    static AtomicStampedReference<String> atomicStampedReference =
            new AtomicStampedReference<>("Python", 1);

    public static void main(String[] args) throws InterruptedException {

        //拿到当前的版本号(旧)
        final int oldStamp = atomicStampedReference.getStamp();
        final String oldReference = atomicStampedReference.getReference();
        System.out.println("oldStamp======"+oldStamp);
        System.out.println("oldReference=="+oldReference);


        Thread rightThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":当前变量值：" +oldReference + "-当前版本戳：" + oldStamp );
                boolean b = atomicStampedReference.compareAndSet(oldReference, "JAVA", oldStamp, oldStamp + 1);
                System.out.println("rightThread==="+b);
                System.out.println("atomicStampedReference.getReference()=="+atomicStampedReference.getReference());
                System.out.println("atomicStampedReference.getStamp()=="+atomicStampedReference.getStamp());
            }
        });


        Thread errorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String reference = atomicStampedReference.getReference();
//                System.out.println(Thread.currentThread().getName()+":当前变量值：" +oldReference + "-当前版本戳：" + oldStamp );
//                boolean b = atomicStampedReference.compareAndSet(oldReference, "JAVA", oldStamp, oldStamp + 1);
//                System.out.println("errorThread==="+b);
//                System.out.println("atomicStampedReference.getReference()=="+atomicStampedReference.getReference());
//                System.out.println("atomicStampedReference.getStamp()=="+atomicStampedReference.getStamp());
//                String reference = atomicStampedReference.getReference();
//                System.out.println(Thread.currentThread().getName() +":当前变量值：" +reference + "-当前版本戳：" + atomicStampedReference.getStamp() + "-"
//                        + atomicStampedReference.compareAndSet(reference,
//                        reference + "+C", atomicStampedReference.getStamp(),
//                        oldStamp + 1));
                boolean b = atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), reference + "+C", atomicStampedReference.getStamp(), oldStamp + 1);
                System.out.println(b);
            }
        });


        rightThread.start();
        rightThread.join();
        errorThread.start();
        errorThread.join();

        System.out.println("最后一行-->"+atomicStampedReference.getReference()+"============"+atomicStampedReference.getStamp());
    }


}
