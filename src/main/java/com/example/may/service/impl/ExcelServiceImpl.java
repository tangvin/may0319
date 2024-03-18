package com.example.may.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.may.mapper.ExcelMapper;
import com.example.may.po.HeadImportDataInfo;
import com.example.may.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/16   11:21
 * @version: 1.0
 * @modified:
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    ExcelMapper excelMapper;

    @Override
    public HeadImportDataInfo query() {
        return excelMapper.selectInfo();
    }

    @Override
    public void batchInsert(List<HeadImportDataInfo> list) {
        excelMapper.batchInsert(list);
    }

    @Override
    public void insertByBatch(List<HeadImportDataInfo> list) {
        long start = System.currentTimeMillis();
        if (CollUtil.isEmpty(list)) {
            return;
        }
        int c = 1000;
        int b = list.size() / c;
        int d = list.size() % c;
        for (int e = c; e <= c * b; e = e + c) {
            excelMapper.batchInsert(list.subList(e - c, e));
        }
        if (d != 0) {
            excelMapper.batchInsert(list.subList(c * b, list.size()));
        }
        System.out.println("使用多线程批量插入耗时：" + (System.currentTimeMillis() - start) + "ms");

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertByExecutorService(List<HeadImportDataInfo> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        /**
         *  //数据集合大小
         *         Integer total = list.size();
         *         Integer size = 5;
         *         //开启的线程数  = 总页数
         *         Integer runSize = total % size == 0 ? total / size : total / size + 1;
         *         // 一个线程处理数据条数，如果库中有100条数据，开启20个线程，那么每一个线程执行的条数就是5条
         *         int count = total / runSize;//5
         */
        // 10000 / 10 =  1000
        int nThread = 5;
        int total = list.size();
        Integer size = 1000;
        //开启的线程数  = 总页数
        Integer runSize = total % size == 0 ? total / size : total / size + 1;
        // 创建一个线程池，数量和开启线程的数量一样
        ExecutorService executorService = Executors.newFixedThreadPool(nThread);
        for (int i = 0; i < runSize; i++) {
//            final List<HeadImportDataInfo> itemlist = list.subList(size / nThread * i, size / nThread * (i + 1));
            Integer toIndex = (i + 1) * size > total ? total : (i + 1) * size;
            int finalI = i;
            final List<HeadImportDataInfo> itemList =  list.subList(finalI * size, toIndex);
            executorService.execute(() -> {
                try {
                    excelMapper.batchInsert(itemList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
