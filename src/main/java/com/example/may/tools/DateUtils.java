package com.example.may.tools;

import cn.hutool.core.date.*;
import cn.hutool.core.lang.Console;

import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/12/06   22:18
 * @version: 1.0
 * @modified:
 */
public class DateUtils {


    public static void nextMonth() {
        DateTime dateTime = new DateTime();
        int year = DateUtil.year(dateTime);
        int month = DateUtil.month(dateTime);
        System.out.println("year==" + year + "   ---month==" + month);

        //当前时间
        Date date = DateUtil.date();
        System.out.println("当前时间date:"+date);
        //当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        System.out.println("当前时间date2:"+date2);
        //当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        System.out.println("当前时间date3:"+date3);
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        System.out.println("当前时间字符串now:"+now);
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();
        System.out.println("当前日期字符串today:"+today);
    }


    public static void test2(){
        Date date = DateUtil.date();
        System.out.println("date"+date);
        //获得年的部分
        int year = DateUtil.year(date);
        //获得月份，从0开始计数
//        int month = DateUtil.month(date);
        //获得月份枚举
        Month month = DateUtil.monthEnum(date);
        System.out.println("month=="+month);

        String dateStr = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr);
        System.out.println("date1=="+date1);

        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);
        System.out.println("beginOfDay=="+beginOfDay );

        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
        System.out.println("endOfDay=="+endOfDay );
    }


    public static void test3(){
        String dateStr = "2017-03-01 22:33:23";
//        Date date = DateUtil.parse(dateStr);
        DateTime date = DateUtil.date();
        System.out.println("date=="+date );

        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, -1);
        System.out.println("newDate=="+newDate );

        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);
        System.out.println("newDate2=="+newDate2 );

        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);
        System.out.println("newDate3=="+newDate3 );
    }

    public static void test4(){
        //昨天
        DateTime yesterday = DateUtil.yesterday();
        System.out.println("yesterday="+yesterday.toString("yyyyMMdd"));
        //明天
        DateTime tomorrow = DateUtil.tomorrow();
        System.out.println("tomorrow="+tomorrow);
        //上周
        DateTime lastWeek = DateUtil.lastWeek();
        System.out.println("lastWeek="+lastWeek);
        //下周
        DateTime nextWeek = DateUtil.nextWeek();
        System.out.println("nextWeek="+nextWeek);
        //上个月
        DateTime lastMonth = DateUtil.lastMonth();
        System.out.println("lastMonth="+lastMonth);
        //下个月
        DateTime nextMonth = DateUtil.nextMonth();
        System.out.println("nextMonth="+nextMonth);
    }

    public static void test5(){
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);
        System.out.println("date1=="+date1);

        String dateStr2 = "2017-04-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);
        System.out.println("date2=="+date2);


        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.WEEK);
        System.out.println("betweenDay=="+betweenDay);
    }

    public static void test6(){
        //Level.MINUTE表示精确到分
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);
        String dateStr2 = "2020-12-01 23:30:23";
        Date date2 = DateUtil.parse(dateStr2);
        String formatBetween = DateUtil.formatBetween(date1,date2, BetweenFormatter.Level.MINUTE);
        //输出：31天1小时
        Console.log(formatBetween);
        System.out.println("formatBetween=="+formatBetween);
    }


    public static void main(String[] args) {
        test6();
    }
}
