package com.example.may.mapper;

import com.example.may.po.AppUser;
import com.example.may.po.HeadImportDataInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/16   11:24
 * @version: 1.0
 * @modified:
 */
@Mapper
public interface AppUserMapper {


    AppUser selectInfo();
    /**
     * 批量插入数据
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List list);
}
