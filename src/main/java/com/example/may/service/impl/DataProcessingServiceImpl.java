package com.example.may.service.impl;

import com.example.may.service.DataProcessingService;

/**
 * @author tangyin@belink.com
 * @version V1.0
 * @title
 * @description
 * @date 2024-03-22 22:21
 */
public class DataProcessingServiceImpl implements DataProcessingService {

    @Override
    public void dataProcess() {
        //第一步：
        //将导入到临时表的数据迁移到正式表
        //t_import_temp  -->  t_mon_202403  这2张表结构一样



        //第二步：更新用户表数据
        //t_app_user表中记录每个用户的客户号和剩余可用积分
        //需要遍历循环上面的临时表数据，将每个用户的数据更新到用户表
        //循环更新注意：更新前先更根据客户号查询t_app_user表是否存在，
        // 存在更新，不存在，新增该用户的同时将可用积分更新


        //第三步：更新积分日志表
        //t_integral_log表中记录着每个用户的的积分变化情况
        //将临时表中的数据循环 更新到 该表中，每个用户增加积分的情况


        //第四步：更新积分记录表的状态
        //所有操作完成，更根据批次号或主键id 将该表的此条记录更新成功审批完成状态

  }
}
