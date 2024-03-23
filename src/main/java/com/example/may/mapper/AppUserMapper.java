package com.example.may.mapper;

import com.example.may.po.AppUser;
import com.example.may.po.TImportTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

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
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List list);

    /**
     * 根据t_import_temp，插入 t_mon_202403 不存在的记录，条件字段：bank_no在t_mon_202403不存在的记录
     */
    int batInsQuery();

    /**
     * 根据pidNos逗号分割子字符串查询用户存在的集合数据
     *
     * @return 用户表集合id，用于批量更新
     */
    List<AppUser> selectByPidNo(@Param("pidNos") String pidNos);

    int batshUpdate(@Param("list") List list);


    int batchInsertOrUpdate(@Param("list") List list);

}
