package com.example.may.tools;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2024/03/17   14:49
 * @version: 1.0
 * @modified:
 */
public class FormatValidatorUtil {

    /**
     * 判断字符串是否为指定格式的日期时间
     *
     * @param dateStr    需要检查的日期字符串
     * @param dateFormat 指定的日期格式，例如："yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd" 等
     * @return 如果字符串是指定格式的日期时间，返回 true;否则返回 false。
     */

    public static boolean isValidDateFormat(String dateStr, String dateFormat) {
        if (ObjectUtil.isEmpty(dateStr)) {
            return false;
        }
        try {
            // 将字符串解析为日期对象，如果解析成功，则说明字符串是有效的日期格式；否则说明字符串不是有效的日期格式。
            DateUtil.parse(dateStr, dateFormat);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
