package com.example.may.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSON;
import com.example.may.common.Result;
import com.example.may.common.BaseRest;
import com.example.may.dto.UserExcelDto;
import com.example.may.dto.UserExcelErrDto;
import com.example.may.easyexcel.EasyExcelListener;
import com.example.may.easyexcel.EasyExcelUtils;
import com.example.may.easyexcel.ExcelCheckErrDto;
import com.example.may.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/17   13:23
 * @version: 1.0
 * @modified:
 */
@RestController
@RequestMapping("/easy")
public class EasyExcelController {

    @Autowired
    private UserService userService;
    /**
     * easyexcel通过validation和正则实现excel导入校验
     * 导入表头校验
     * 导入数据校验
     * 导入业务逻辑校验
     */
    @PostMapping("/importExcel")
    public Result importExcel(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {
        EasyExcelListener easyExcelListener = new EasyExcelListener(userService, UserExcelDto.class);
        EasyExcelFactory.read(file.getInputStream(), UserExcelDto.class, easyExcelListener).sheet().doRead();
        List<ExcelCheckErrDto<UserExcelDto>> errList = easyExcelListener.getErrList();
        if (!errList.isEmpty()) {
            //如果包含错误信息就导出错误信息
            exportErrorMsg(response, errList);
        }
        return new Result();
    }

    private static void exportErrorMsg(HttpServletResponse response, List<ExcelCheckErrDto<UserExcelDto>> errList) throws IOException {
        List<UserExcelErrDto> excelErrDtos = errList.stream().map(excelCheckErrDto -> {
            UserExcelErrDto userExcelErrDto = JSON.parseObject(JSON.toJSONString(excelCheckErrDto.getT()), UserExcelErrDto.class);
            userExcelErrDto.setErrMsg(excelCheckErrDto.getErrMsg());
            return userExcelErrDto;
        }).collect(Collectors.toList());
        EasyExcelUtils.webWriteExcel(response, excelErrDtos, UserExcelErrDto.class, "用户导入错误信息");
    }
}
