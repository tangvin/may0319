package com.example.may.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.may.po.HeadImportDataInfo;
import com.example.may.response.ReturnResult;
import com.example.may.service.ExcelService;
import com.example.may.tools.FormatValidatorUtil;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/16   10:26
 * @version: 1.0
 * @modified:
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {


    /**
     * 全局样式map，避免【The maximum number of Cell Styles was exceeded. You can define up to 64000 style in a .xlsx Workbook】
     */
    private Map<String, CellStyle> styleMap = new HashMap<String, CellStyle>();

    @Autowired
    ExcelService excelService;

    @ResponseBody
    @RequestMapping(value = "/exportTemplate", method = RequestMethod.GET)
    public void moban(HttpServletRequest request, HttpServletResponse response) {

        ExcelWriter writer = ExcelUtil.getWriter(true);
//        List<String> row = CollUtil.newArrayList("importDate", "pidNo", "bankNo", "bankName","point","validateDate");
        List<String> secRow = CollUtil.newArrayList("导入日期", "客户号", "归属行号", "归属行名称", "赠送积分", "积分有效期");
//        writer.writeHeadRow(row);
        writer.writeHeadRow(secRow);
        /** 这里说是隐藏，但是其实就是将高度置为0，有点鸡肋 */
//        writer.getSheet().getRow(0).setZeroHeight(true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=import_template.xlsx");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
            // 关闭writer，释放内存
            writer.close();
            //此处记得关闭输出Servlet流
            IoUtil.close(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //导入excel
    @PostMapping("/import")
    public ReturnResult importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        long l = System.currentTimeMillis();
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        if (file == null || file.isEmpty()) {
            return ReturnResult.failed(HttpStatus.BAD_REQUEST.value(), "上传文件为空，请重新上传");
        } else if (!suffix.equals("xls") && !suffix.equals("xlsx")) {
            return ReturnResult.failed(HttpStatus.BAD_REQUEST.value(), "文件格式错误，请上传excel文件");
        }

        // 逐行分析表格数据，从 sheet=0 读取
        try (InputStream is = file.getInputStream()) {
            ExcelWriter writer = ExcelUtil.getWriter(true);
            writer.renameSheet("导入错误提示数据提示");
            // 可导入数据
            List<HeadImportDataInfo> excelDTOList = new ArrayList<>();
            //收集客户号
            List<String> custList = new ArrayList<>();
            long l1 = System.currentTimeMillis();
            ExcelUtil.readBySax(is, 0, (sheetIndex, rowIndex, rowList) -> {
                // 去除标题行
                //判断第一行表头，和需要读取的表头数据是否一直
                //跳过空行

                if (rowIndex == 0) {
                    //跳过空行
                    if (rowList == null || rowList.size() == 0 || rowList.get(0).equals("")) {
                        return;
                    }
                    System.out.println("这是第一行表头数据:" + rowList);
                }

                // 第2行开始，一次读取一行数据
                if (rowIndex > 1) {

                    //验证是否满足导入条件(格式+数据库查重)
                    HeadImportDataInfo headImportDataInfo = validExcelData(rowList, writer, rowIndex, custList);
                    System.out.println("第" + rowIndex + "行:" + headImportDataInfo);
                    //校验通过-通过的数据加入的list
                    if (headImportDataInfo != null) {
                        excelDTOList.add(headImportDataInfo);
                    }
                }
            });
            System.out.println("校验耗时：" + (System.currentTimeMillis() - l1) + " ms");
            // 无法导入的用户数量
            int errorRowCount = writer.getPhysicalRowCount();
            // 校验成功行数
            int successRows = excelDTOList.size();
            System.out.println("无法导入的用户数量=" + errorRowCount);


            if (!CollectionUtils.isEmpty(excelDTOList)) {
                System.out.println("校验成功的数据 " + successRows + " 条");
                System.out.println("准备数据入库。。。。。");
//                excelService.insertByExecutorService(excelDTOList);
                excelService.insertByExecutorService(excelDTOList);

            }

            // 每次导出都初始化样式map，避免非同源错误
            this.styleMap = new HashMap<String, CellStyle>();


            // 将无法导入的用户列表写入到错误文件中
            if (errorRowCount > 0) {
                String fileName = System.currentTimeMillis() + "_导入文件错误提示.xlsx";
                // 把导入的错误信息xls文件，上传至服务器
                String fileDir = "C:/template/error/";
                String filePath = fileDir + fileName + "_" + "error.xlsx";
                String absolutePath = FileUtil.getAbsolutePath(filePath);
                System.out.println("absolutePath-->" + absolutePath);

                // 将错误内容输出到文件
                FileOutputStream outputStream = new FileOutputStream(FileUtil.getAbsolutePath(filePath));
                writer.flush(outputStream, true);
                writer.close();
                outputStream.close();
            }


            System.out.println("校验+入库一共耗时：" + (System.currentTimeMillis() - l) / 1000 + "s");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    private HeadImportDataInfo validParams(List<Object> rowList, StringBuilder errorDesc, List<Integer> cols, List<String> pidNoList) {
        HeadImportDataInfo headImportDataInfo = new HeadImportDataInfo();
        //创建一个以1为增量步长，从start(包括)到end(不包括)的有序的数字流。
        IntStream.range(0, 6).forEach(i -> {
            //i 表示 第几列
            String rowContent = null;
            if (CollUtil.isEmpty(rowList) && rowList.size() < 6) {
                return;
            }
            Object obj = null;
            if (rowList.size() > 0 && rowList.get(i) != null) {
                obj = rowList.get(i);
            }
            //去除前后空格
            if (obj != null) {
                rowContent = StrUtil.trim(obj.toString());
            }
            //非空校验
            if (StrUtil.isEmpty(rowContent)) {
                cols.add(i);
                if (!errorDesc.toString().contains("输入有空")) {
                    errorDesc.append("输入有空").append("、");
                }
            }
            //获取文字内容长度校验
            int rowContentLength = StrUtil.length(rowContent);

            //以下根据具体的Excel表格列属性分别进行不同校验

            //第一列 导入日期
            if (i == 0) {
                if (!FormatValidatorUtil.isValidDateFormat(rowContent, "yyyy-MM-dd")) {
                    if (!errorDesc.toString().contains("导入日期不正确")) {
                        cols.add(i);
                        errorDesc.append("导入日期不正确").append("、");
                    }
                } else {
                    headImportDataInfo.setImportDate(rowContent);
                }
                //第二列 客户号
            } else if (i == 1) {
                if (rowContentLength <= 0) {
                    cols.add(i);
                    if (!errorDesc.toString().contains("非法客户号")) {
                        errorDesc.append("非法客户号").append("、");
                    }
                }
                if (pidNoList.contains(rowContent)) {
                    cols.add(i);
                    if (!errorDesc.toString().contains("客户号已经存在")) {
                        errorDesc.append("客户号已经存在").append("、");
                    }
                } else {
                    headImportDataInfo.setPidNo(rowContent);
                    pidNoList.add(rowContent);
                }
                //第三列 归属行号
            } else if (i == 2) {
                if (cols.isEmpty()) {
                    headImportDataInfo.setBankNo(rowContent);
                }
                //第四列 归属行名称
            } else if (i == 3) {
                if (cols.isEmpty()) {
                    headImportDataInfo.setBankName((String) obj);
                }
                //第五列 赠送积分
            } else if (i == 4) {
                if (!NumberUtil.isNumber(rowContent)) {
                    cols.add(i);
                    if (!errorDesc.toString().contains("非法积分值")) {
                        errorDesc.append("非法积分值").append("、");
                    }
                } else {
                    BigDecimal bigDecimal = new BigDecimal(rowContent);
                    headImportDataInfo.setRewardPoint(bigDecimal);
                }
                //第六列 积分有效期
            } else if (i == 5) {
                if (!FormatValidatorUtil.isValidDateFormat(rowContent, "yyyy-MM-dd")) {
                    cols.add(i);
                    if (!errorDesc.toString().contains("非法积分值")) {
                        errorDesc.append("非法积分值").append("、");
                    }
                } else {
                    headImportDataInfo.setExpirationDate(rowContent);
                }
            }

        });
        if (StrUtil.isNotBlank(errorDesc)) {
            // 去除最后一个分隔符
            errorDesc.deleteCharAt(errorDesc.lastIndexOf("、"));
        }
        if (cols.isEmpty()) {
            return headImportDataInfo;
        } else {
            return null;
        }
    }


    /**
     * 验证入库数据
     *
     * @param rowList 行数据
     * @param writer  excel写入者
     * @return 参数校验失败则返回null， 校验成功则返回 ArtisanExcelDTO 对象
     */
    private HeadImportDataInfo validExcelData(List<Object> rowList, ExcelWriter writer, Long sort, List<String> custList) {

        //1、校验整行空数据
//        if (isNull(rowList) == 0) {
//            return null;
//        }
        // 错误描述
        StringBuilder errorDesc = new StringBuilder();
        // 标黄单元格列表
        List<Integer> cols = new ArrayList<>();

        // 2、对行中数据进行校验
        HeadImportDataInfo headImportDataInfo = validParams(rowList, errorDesc, cols, custList);

        // todo 3、如果无单元格需要标黄，则证明本行数据验证成功
        if (cols.isEmpty() && headImportDataInfo != null) {
//            HeadImportDataInfo headImportData = assembleData(rowList);
            return headImportDataInfo;
        } else {
            // 将错误原因写入最后一列
            rowList.add(errorDesc.toString());
            // 写入本行数据
            writer.writeRow(rowList);
            // 创建样式并设置背景颜色为黄色
//            CellStyle cellStyle = createCellStyle(writer);
//            for (Integer col : cols) {
//                writer.setStyle(cellStyle, col, writer.getRowCount() - 1);
//            }
            return null;
        }
    }


    private HeadImportDataInfo assembleData(List<Object> rowList) {
        HeadImportDataInfo headImportData = new HeadImportDataInfo();

        return headImportData;

    }

    /**
     * 获取单元格格式---黄色高亮
     */
    @NotNull
    public CellStyle createCellStyle(ExcelWriter writer) {
        CellStyle cellStyle = null;
        // 判断全局样式集合
        if (this.styleMap.isEmpty()) {
            // 为空则创建
            cellStyle = writer.createCellStyle();
            this.styleMap.put("1", cellStyle);
        } else {
            cellStyle = this.styleMap.get("1");
        }
//        CellStyle cellStyle = writer.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置四周边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        // 设置内容居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    /**
     * 校验表格每一行是否是空数据<br/>
     * 对一整行空数据过滤
     *
     * @return 是否一整行是空数据
     */
    private int isNull(List<Object> rowList) {
        int empty = 0;
        for (Object o : rowList) {
            if (o != null && !"".equals(o) && !" ".equals(o)) {
                empty++;
            }
        }
        return empty;
    }

}
