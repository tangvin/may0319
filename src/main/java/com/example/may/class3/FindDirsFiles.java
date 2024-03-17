package com.example.may.class3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/23   16:15
 * @version: 1.0
 * @modified:
 */
public class FindDirsFiles extends RecursiveAction {

    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }


    @Override
    protected void compute() {
        ArrayList<FindDirsFiles> subTasks = new ArrayList<>();
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //对每一个子目录都新建一个任务
                    subTasks.add(new FindDirsFiles(file));
                } else {
                    //遇到文集，检查
                    if (file.getAbsolutePath().endsWith("txt")) {
                        System.out.println("文件:" + file.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()) {
                //在当前的ForkJoinPool上面调度所有的子任务
                Collection<FindDirsFiles> findDirsFiles = invokeAll(subTasks);
                for (FindDirsFiles subTask : findDirsFiles) {
                    subTask.join();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ForkJoinPool pool = new ForkJoinPool();
            FindDirsFiles task = new FindDirsFiles(new File("D://testBIN"));
            pool.execute(task);
            System.out.println("Task is Running......");

            Thread.sleep(1);
            int otherWork = 0;
            for (int i = 0; i < 100; i++) {
                otherWork = otherWork+i;
            }
            System.out.println("Main Thread done sth......,otherWork="+otherWork);
            task.join();
            System.out.println("Task end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
