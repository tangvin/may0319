package com.example.may.class3;

import com.example.may.tools.SleepTools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @description: 统计整形数组中所有元素的和(使用ForkJoin)
 * @author: Bruce_T
 * @date: 2022/05/23   15:55
 * @version: 1.0
 * @modified:
 */
public class UseForkJoinSum {

    private static class SumTask extends RecursiveTask<Integer> {

        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
        private int[] source;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] source, int fromIndex, int toIndex) {
            this.source = source;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                System.out.println(" fromIndex = " + fromIndex + " toIndex=" + toIndex);
                int count = 0;
                for (int i = fromIndex; i <= toIndex; i++) {
                    SleepTools.ms(1);
                    count = count + source[i];
                }
                return count;
            } else {
                //fromIndex....mid.....toIndex
                int middleIndex = (fromIndex + toIndex) / 2;
                SumTask left = new SumTask(source, fromIndex, middleIndex);
                SumTask right = new SumTask(source, middleIndex + 1, toIndex);
                //同步提交
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();
        SumTask sumTask = new SumTask(src, 0, src.length - 1);
        long start = System.currentTimeMillis();
        pool.invoke(sumTask);
        System.out.println("Task is Running.....");
        System.out.println("The count is " + sumTask.join() + " spend time:" + (System.currentTimeMillis() - start) + "ms");
    }

}
