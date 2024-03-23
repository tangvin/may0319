package com.example.may.mapper;

import com.example.may.po.TImportTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TImportTempMapper {

    /**
     * 查询当前临时表总记录数
     */
    Integer countTImportTemp();

    /**
     * 分页查询
     *
     * @param offset   跳过多少条数据
     * @param pageSize 每页多少条数据
     */
    List<TImportTemp> selectPagedTemp(@Param("offset") int offset, @Param("pageSize") int pageSize);

}
