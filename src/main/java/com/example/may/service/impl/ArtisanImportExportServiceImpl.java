package com.example.may.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/15   23:27
 * @version: 1.0
 * @modified:
 */
public class ArtisanImportExportServiceImpl {

    /**
     * 中文名字通常符合这样的规则：中文字符（\u4E00-\u9FA5）+ 空格/中文字符
     */
//    private static final String NAME_PATTERN = "[\\u4E00-\\u9FA5]+[\\s]*[\\u4E00-\\u9FA5]+";
    private static final String NAME_PATTERN = "[[\\u4e00-\\u9fa5]|·|-|●|(\\(+\\))|(\\([\\u4e00-\\u9fa5]+\\))]+";
    /**
     * 正则<br/>
     * 从详细地址中提取省市县名称
     */
    private static final String AREA_REGEX = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
    private static Pattern AREA_PATTER = Pattern.compile(AREA_REGEX);


    private static List<String> headList = new ArrayList<>();

    static {
        headList.add("序号");
        headList.add("姓名");
        headList.add("性别");
        headList.add("出生年月");
        headList.add("民族");
        headList.add("学历");
        headList.add("政治面貌");
        headList.add("身份证号");
        headList.add("工作单位");
        headList.add("职务");
        headList.add("行业");
        headList.add("工种");
        headList.add("职称");
        headList.add("技术等级");
        headList.add("何时参加工作");
        headList.add("生产现场工作年限");
        headList.add("单位性质");
        headList.add("人员结构");
        headList.add("是否农民工");
        headList.add("主要荣誉");
        headList.add("技术技能（工匠）奖项");
        headList.add("同行高级专家1姓名单位职务");
        headList.add("同行高级专家2姓名单位职务");
        headList.add("同行高级专家3姓名单位职务");
        headList.add("推荐理由");
        headList.add("一句话推荐理由");
        headList.add("单位地址");
        headList.add("邮编");
        headList.add("推荐对象联系电话");
        headList.add("推荐单位");
        headList.add("推荐单位联系人");
        headList.add("联系人电话");
        headList.add("导入错误提示");
    }

    public void importSummaryExcel(MultipartFile file, HttpServletResponse response) {
        try (InputStream is = file.getInputStream()) {
            // 创建ExcelWriter，生成xls
            ExcelWriter writer = ExcelUtil.getWriter(true);
            writer.renameSheet("大国工匠培育对象推荐汇总表-导入错误提示数据");
            // 表头
            writer.writeHeadRow(headList);
            // 最后一列
            writer.getSheet().setColumnWidth(32, 20000);
            CellStyle style = writer.getWorkbook().createCellStyle();
            style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            writer.setStyle(style, 32, 0);

            // 可导入数据
            List<Object> excelDTOList = new ArrayList<>();
            // 逐行分析表格数据，从 sheet=0 读取
            ExcelUtil.readBySax(is, 0, (sheetIndex, rowIndex, rowList) -> {
                if (rowIndex == 2 && (rowList.size() != (headList.size() - 1))) {
                    throw new RuntimeException("未能按照Excel模板填写数据！");
                }
                if (rowIndex == 3 && !"示例".equals(rowList.get(0))) {
                    throw new RuntimeException("导入失败，未能按照Excel模板填写数据，示例行不能删除！");
                }
                // 去除示例行
                // 第2行开始，一次读取一行数据
                if (rowIndex > 3) {
                    // 验证是否满足导入条件(格式+数据库查重)
                    validExcelData(rowList, writer, rowIndex);
                    Object obj = null;
                    if (obj != null) {
                        excelDTOList.add(obj);
                    }
                }
            });
        }catch (Exception e){

        }
    }


    /**
     * 验证入库数据
     *
     * @param rowList 行数据
     * @param writer  excel写入者
     * @return 参数校验失败则返回null， 校验成功则返回 ArtisanExcelDTO 对象
     */
    private Object validExcelData(List<Object> rowList, ExcelWriter writer, Long sort) {
        // todo 1、校验整行空数据
        if (isNull(rowList) == 0) {
            return null;
        }
        // 错误描述
        StringBuilder errorDesc = new StringBuilder();
        // 标黄单元格列表
        List<Integer> cols = new ArrayList<>();

        // todo 2、对行中数据进行校验
        validParams(rowList, errorDesc, cols);

        // todo 3、如果无单元格需要标黄，则证明本行数据验证成功
        if (cols.isEmpty()) {
            // todo 获取所有字典数据
//            List<DictTypeDTO> dictDTOS = dictTypeService.getDict();
//            ArtisanExcelDTO excelDTO = new ArtisanExcelDTO();
//            // 基础信息
//            excelDTO.setPInfoDTO(getInfoDTO(rowList, dictDTOS, sort));
//            // 工作信息
//            excelDTO.setWorkDTO(getWorkDTO(rowList, excelDTO.getPInfoDTO(), dictDTOS));
//            // 荣誉信息
//            excelDTO.setHonorList(getHonorList(rowList, excelDTO.getPInfoDTO()));
//            // 技能（工匠）奖项
//            excelDTO.setRewardList(getRewardList(rowList, excelDTO.getPInfoDTO()));
//            // 三位同行专家
//            excelDTO.setExprecInfo(getExprecInfo(rowList, excelDTO.getPInfoDTO()));
//            // 推荐单位
//            excelDTO.setRecommUnitDTO(getRecommUnitDTO(rowList, excelDTO.getPInfoDTO()));
            return null;
        } else {
           return null;
        }
    }

    /**
     * 校验表格每一行是否是空数据<br/>
     * 对一整行空数据过滤，对列：0、4为公式或固定参数排除掉，剩余数据全为空则一整行数据为空
     *
     * @return 是否一整行是空数据
     */
    private int isNull(List<Object> rowList) {
        int empty = 0;
        for (int i = 0; i < rowList.size(); i++) {
            if (i == 0 || i == 3) {
                continue;
            }
            if (rowList.get(i) != null && !"".equals(rowList.get(i)) && !" ".equals(rowList.get(i))) {
                empty++;
            }
        }
        return empty;
    }


    /**
     * 对行中数据进行校验<br/>
     * 必填项验证 去除序号列，前10列为必填
     *
     * @param errorDesc 错误描述
     * @param cols      标黄单元格列表
     */
    private void validParams(List<Object> rowList, StringBuilder errorDesc, List<Integer> cols) {
        // xls从1开始到32列：1-32
        // 代码从0开始：0-31，过滤掉0列
        IntStream.range(1, 32).forEach(i -> {
            String rowContent = null;
            Object obj = rowList.get(i);
            if (obj != null) {
                // 去除前后空格
                rowContent = StrUtil.trim(obj.toString());
            }
            // 文字内容长度
            int rowContentLength = StrUtil.length(rowContent);
            // 非空校验
            if (i != 3 && StrUtil.isEmpty(rowContent)) {
                // 出生年月不做非空校验
                cols.add(i);
                if (!errorDesc.toString().contains("")) {
                    errorDesc.append("").append("、");
                }
            } else if (i == 1) {
                // 第 1 列，姓名
                if (rowContentLength > 50) {
                    // 50 字以内
                    cols.add(i);
                    if (!errorDesc.toString().contains("")) {
                        errorDesc.append("").append("、");
                    }
                }
                if (!ReUtil.isMatch(NAME_PATTERN, rowContent)) {
                    // 姓名不合法
                    cols.add(i);
                    if (!errorDesc.toString().contains("")) {
                        errorDesc.append("").append("、");
                    }
                }
            } else if (i == 4) {
                // 第 4 列，民族
                if (rowContentLength > 20) {
                    // 20 字以内
                    cols.add(i);
                    if (!errorDesc.toString().contains("")) {
                        errorDesc.append("").append("、");
                    }
                }
            } else if (i == 5) {
                // 第 5 列，学历
                if (rowContentLength > 20) {
                    // 20 字以内
                    cols.add(i);
                    if (!errorDesc.toString().contains("")) {
                        errorDesc.append("").append("、");
                    }
                }
            } else if (i == 6) {
                // 第 6 列，政治面貌
                if (rowContentLength > 20) {
                    // 20 字以内
                    cols.add(i);
                    if (!errorDesc.toString().contains("")) {
                        errorDesc.append("").append("、");
                    }
                }
            } else if (i == 7) {
                // 第 7 列，身份证号
                if (!IdcardUtil.isValidCard(rowContent)) {
                    // 此身份证号不合法
                    cols.add(i);
                }

            } else if (i == 8) {
                // 第 8 列，工作单位

            } else if (i == 9) {
                // 第 9 列，职务

            } else if (i == 10) {
                // 第 10 列，行业

            } else if (i == 11) {
                // 第 11 列，工种

            } else if (i == 12) {
                // 第 12 列，职称
                if (rowContentLength > 10) {
                    // 10 字以内
                    cols.add(i);

                }
            } else if (i == 13) {
                // 第 13 列，技术等级
                if (rowContentLength > 10) {
                    // 10 字以内
                    cols.add(i);

                }
            } else if (i == 14) {
                // 第 14 列，何时参加工作
                if (!rowContent.matches("^\\d{4}-\\d{2}$")) {
                    // 何时参加工作日期格式不正确
                    cols.add(i);

                }
            } else if (i == 15) {
                // 第 15 列，生产现场工作年限
                if (!NumberUtil.isNumber(rowContent)) {
                    // 生产现场工作年限 数量
                    cols.add(i);

                }
                if (rowContentLength > 20) {
                    // 20 字以内
                    cols.add(i);

                }
            } else if (i == 16) {
                // 第 16 列，单位性质
                if (rowContentLength > 10) {
                    // 10 字以内
                    cols.add(i);

                }
            } else if (i == 17) {
                // 第 16 列，人员结构
                if (rowContentLength > 20) {
                    // 20 字以内
                    cols.add(i);

                }
            } else if (i == 18) {
                // 第 18 列，是否农民工
                if (rowContentLength > 10) {
                    // 10 字以内
                    cols.add(i);

                }
            } else if (i == 19 || i == 20) {
                // 第 19 列，主要荣誉
                // 第 20 列，技能工匠奖项
                String msg = i == 19 ? "每一个荣誉名称内容200字以内" : "每一个技术技能（工匠）奖项名称内容200字以内";
                if (rowContent.contains("#")) {
                    for (String str : rowContent.split("#")) {
                        if (StrUtil.length(str) > 200) {
                            // 200 字以内
                            cols.add(i);
                            if (!errorDesc.toString().contains(msg)) {
                                errorDesc.append(msg).append("、");
                            }
                            break;
                        }
                    }
                } else {
                    if (rowContentLength > 200) {
                        cols.add(i);
                        if (!errorDesc.toString().contains(msg)) {
                            errorDesc.append(msg).append("、");
                        }
                    }
                }
            } else if (i == 21 || i == 22 || i == 23) {
                // 第 21 列，同行高级专家1姓名单位职务
                // 第 22 列，同行高级专家2姓名单位职务
                // 第 23 列，同行高级专家3姓名单位职务
                String nameMsg = "同行高级专家" + (i == 21 ? 1 : i == 22 ? 2 : 3) + "姓名50字以内";
                String unitMsg = "同行高级专家" + (i == 21 ? 1 : i == 22 ? 2 : 3) + "单位100字以内";
                String jobMsg = "同行高级专家" + (i == 21 ? 1 : i == 22 ? 2 : 3) + "职务100字以内";
                if (rowContent.contains("#")) {
                    String[] name1Str = rowContent.split("#");
                    if (StrUtil.length(name1Str[0]) > 50) {
                        // 姓名、50 字以内
                        cols.add(i);
                        if (!errorDesc.toString().contains(nameMsg)) {
                            errorDesc.append(nameMsg).append("、");
                        }
                    }
                    if (name1Str.length > 1 && StrUtil.length(name1Str[1]) > 150) {
                        // 单位、100 字以内
                        cols.add(i);
                        if (!errorDesc.toString().contains(unitMsg)) {
                            errorDesc.append(unitMsg).append("、");
                        }
                    }
                    if (name1Str.length > 2 && StrUtil.length(name1Str[2]) > 150) {
                        // 职务、100 字以内
                        cols.add(i);
                        if (!errorDesc.toString().contains(jobMsg)) {
                            errorDesc.append(jobMsg).append("、");
                        }
                    }
                } else {
                    if (rowContentLength > 50) {
                        // 50 字以内
                        cols.add(i);
                        if (!errorDesc.toString().contains(nameMsg)) {
                            errorDesc.append(nameMsg).append("、");
                        }
                    }
                }
            }  else if (i == 27) {
                // 第 27 列，邮编
                if (rowContentLength > 20) {
                    // 邮编 20字以内
                    cols.add(i);

                }
                if (!Validator.isNumber(rowContent)) {
                    // 邮编只能是数字
                    cols.add(i);

                }
            } else if (i == 28) {
                // 第 28 列，推荐对象联系电话
                if (rowContentLength > 30) {
                    // 推荐对象联系电话 30字以内
                    cols.add(i);

                }
                if (!Validator.isMobile(rowContent)) {
                    // 电话不合法
                    cols.add(i);
                }
            }
        });
        if (StrUtil.isNotBlank(errorDesc)) {
            // 去除最后一个分隔符
            errorDesc.deleteCharAt(errorDesc.lastIndexOf("、"));
        }
    }
}
