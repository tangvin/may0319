package com.example.may.class1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @description: 只有main方法的线程
 * @author: Bruce_T
 * @date: 2022/05/21   14:00
 * @version: 1.0
 * @modified:
 */
public class OnlyMainThread {
    public static void main(String[] args) {
        //java 虚拟机线程系统的管理接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        /*不需要获取同步的monitor和synchronizer信息，仅仅获取线程和线程堆栈信息*/
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        /*遍历线程信息，仅打印线程ID和线程名称信息*/
        for (ThreadInfo threadInfo : threadInfos) {
//            System.out.println("[" + threadInfo.getThreadId() + "]:" + "[" + threadInfo.getThreadName() + "]");
            if(threadInfo.getThreadName().contains("main")){
                System.out.println("[" + threadInfo.getThreadId() + "]:" + "[" + threadInfo.getThreadName() + "]-->//main线程，用户程序入口");
            }
            if(threadInfo.getThreadName().contains("Reference")){
                System.out.println("[" + threadInfo.getThreadId() + "]:" + "[" + threadInfo.getThreadName() + "]-->//清除Reference的线程");
            }
            if(threadInfo.getThreadName().contains("Finalizer")){
                System.out.println("[" + threadInfo.getThreadId() + "]:" + "[" + threadInfo.getThreadName() + "]-->//调用对象finalize方法的线程");
            }
            if(threadInfo.getThreadName().contains("Signal")){
                System.out.println("[" + threadInfo.getThreadId() + "]:" + "[" + threadInfo.getThreadName() + "]-->//分发处理发送给JVM信号的线程");
            }
            if(threadInfo.getThreadName().contains("Attach")){
                System.out.println("[" + threadInfo.getThreadId() + "]:" + "[" + threadInfo.getThreadName() + "]-->//内存dump，线程dump，类信息统计，获取系统属性等");
            }
            if(threadInfo.getThreadName().contains("Monitor")){
                System.out.println("[" + threadInfo.getThreadId() + "]:" + "[" + threadInfo.getThreadName() + "]-->//监控Ctrl-Break中断信号的");
            }

        }
    }
}
