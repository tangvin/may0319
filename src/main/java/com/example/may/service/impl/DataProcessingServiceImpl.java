package com.example.may.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.example.may.mapper.AppUserMapper;
import com.example.may.mapper.TImportTempMapper;
import com.example.may.po.AppUser;
import com.example.may.po.TImportTemp;
import com.example.may.service.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author tangyin@belink.com
 * @version V1.0
 * @title
 * @description
 * @date 2024-03-22 22:21
 */
@Service
public class DataProcessingServiceImpl implements DataProcessingService {

    @Autowired
    AppUserMapper appUserMapper;
    @Autowired
    TImportTempMapper tImportTempMapper;

    @Override
    public void dataProcess() {
        long a = System.currentTimeMillis();
        //第1步：
        //将导入到临时表的数据迁移到正式表
        //t_import_temp  -->  t_mon_202403  这2张表结构一样
        appUserMapper.batInsQuery();

        // 查询临时表总记录数，进行分页查询该表全量数据
        Integer totalCount = tImportTempMapper.countTImportTemp();
        // 总页数，每页5000条
        int pageSize = 5000;
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        for (int page = 1; page <= totalPages; page++) {
            int offset = (page - 1) * pageSize;
            int currentPageSize = Math.min(pageSize, totalCount - offset);

            long a1 = System.currentTimeMillis();

            // 分页查询临时表数据，进行下面的处理数据
            List<TImportTemp> temps = tImportTempMapper.selectPagedTemp(offset, currentPageSize);
            // todo: 第2步，更新用户表数据
            List<String> pidNos = temps.stream().map(i -> i.getPidNo()).collect(Collectors.toList());
            List<AppUser> appUserList = appUserMapper.selectByPidNo(StrUtil.join(", ", pidNos).replaceAll(" ", ""));
            // 查询 t_app_user 里是不是有该用户号数据，有则累加金额
            if (CollUtil.isNotEmpty(appUserList)) {
                // 不为空，更新用户信息
                temps.forEach(sourceItem -> {
                    Optional<AppUser> targetItemOpt = appUserList.stream()
                            .filter(item -> item.getPidNo().equals(sourceItem.getPidNo()))
                            .findFirst();
                    targetItemOpt.ifPresent(targetItem -> {
                        targetItem.setPidNo(targetItem.getPidNo());
                        targetItem.setBankNo(sourceItem.getBankNo());
                        targetItem.setBankName(sourceItem.getBankName());
                        targetItem.setRemainPoint(targetItem.getRemainPoint().add(sourceItem.getRewardPoint()));
                    });
                });
            }
            // 过滤掉 t_app_user 已经存在的数据，并把上面的集合与当前集合合并
            Map<String, Long> userMap = appUserList.stream().collect(Collectors.toMap(AppUser::getPidNo, s -> 1L));
            List<AppUser> users = temps.stream().filter(t -> userMap.get(t.getPidNo()) == null).map(temp -> {
                AppUser appUser = new AppUser();
                appUser.setPidNo(temp.getPidNo());
                appUser.setBankNo(temp.getBankNo());
                appUser.setBankName(temp.getBankName());
                appUser.setRemainPoint(temp.getRewardPoint());
                return appUser;
            }).collect(Collectors.toList());
            // 合并集合后，批量操作：有则更新没有则新增操作
            appUserList.addAll(users);
            appUserMapper.batchInsertOrUpdate(appUserList);

            System.out.println("每次循环 有则更新没有则新增操作耗时：" + (System.currentTimeMillis() - a1) + " ms");

            // todo: 第3步，更新积分日志表

            // todo:第四步：更新积分记录表的状态

        }

        System.out.println("总耗时：" + (System.currentTimeMillis() - a) + " ms");

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
