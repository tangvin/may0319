package com.example.may.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.may.po.HeadImportDataInfo;
import com.example.may.service.DataProcessingService;
import com.example.may.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/21   13:25
 * @version: 1.0
 * @modified:
 */
@RestController
public class HelloWord {

    @Autowired
    ExcelService excelService;

    @Autowired
    DataProcessingService dataProcessingService;

    @RequestMapping("/hello")
    public String hello(){
        dataProcessingService.dataProcess();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        int i = atomicInteger.get();
        System.out.println("==========="+i);
        return "Hello World!";
    }

    @RequestMapping("/get")
    public String get(){
        HeadImportDataInfo query = excelService.query();
        System.out.println("query-->"+query);
        return "ok";
    }

    //表格导出接口
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        //在内存操作，写到浏览器
        ExcelWriter writer= ExcelUtil.getWriter(true);

        //自定义标题别名
        //导入日期", "客户号", "归属行号", "归属行名称", "赠送积分", "积分有效期"
        writer.addHeaderAlias("importDate","导入日期");
        writer.addHeaderAlias("password","客户号");
        writer.addHeaderAlias("nickname","归属行号");
        writer.addHeaderAlias("email","归属行名称");
        writer.addHeaderAlias("phone","赠送积分");
        writer.addHeaderAlias("address","积分有效期");


        assembleData();






        //默认配置
//        writer.write(list,true);
        //设置content—type
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");

        //设置标题
        String fileName= URLEncoder.encode("用户信息","UTF-8");
        //Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream outputStream= response.getOutputStream();

        //将Writer刷新到OutPut
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
    }

    private List<HeadImportDataInfo> assembleData(){
        ArrayList<HeadImportDataInfo> arrayList = new ArrayList<>();
        HeadImportDataInfo dataInfo = new HeadImportDataInfo();


        arrayList.add(dataInfo);
        return arrayList;
    }


}
