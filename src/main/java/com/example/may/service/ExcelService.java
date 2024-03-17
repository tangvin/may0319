package com.example.may.service;

import com.example.may.po.HeadImportDataInfo;

import java.util.List;

/**
 * @description:
 * @author: Bruce_T+
 * @date: 2024/03/16   11:21
 * @version: 1.0
 * @modified:
 */
public interface ExcelService {




    HeadImportDataInfo query();

    void batchInsert(List<HeadImportDataInfo> importList);

    void insertByBatch(List<HeadImportDataInfo> importList);


    void insertByExecutorService(List<HeadImportDataInfo> importList);
}
