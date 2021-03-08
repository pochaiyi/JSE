package com.toJSE.commonClass;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Java日期时间类测试
 */
public class DateTest {
    // System.currentTimeMillis()
    @Test
    public void test1(){
        long time1 = System.currentTimeMillis();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        System.out.println((time2-time1)/1000);
    }

    // java.util.Date
    @Test
    public void util_Date_test(){
        // 空参实例化
        Date date1 = new Date();
        // toString()
        System.out.println(date1); //Wed Mar 03 13:30:14 CST 2021
        // getTime()
        System.out.println(date1.getTime()); //1614749414917
        // 使用时间戳实例化
        Date date2 = new Date(date1.getTime());
        System.out.println(date2);
    }

    // java.sql.Date
    @Test
    public void sql_Date_test(){
        // 主要用于解决数据库中的日期类型
        java.sql.Date date1 = new java.sql.Date(2020-1900,10-1,1);
        System.out.println(date1); //2020-10-01
        // 使用时间戳实例化，同时可以util.Date()->sql.Date()
        java.sql.Date date2 = new java.sql.Date(new Date().getTime());
        System.out.println(date2); //2021-03-03
    }

    // SimpleDateFormat
    @Test
    public void test3(){
        // 日期->字符串
        // 空参构造器
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
        Date date1 = new Date();
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println(date1); //Wed Mar 03 21:12:22 CST 2021
        System.out.println(dateStr1); //21-3-3 下午9:12

        // 字符串->日期
        String datestr2 = "2021-3-3 上午12:30";
        try {
            Date dateParse = simpleDateFormat1.parse(datestr2);
            System.out.println(dateParse); //Wed Mar 03 00:30:00 CST 2021
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 自定义格式，使用带参构造器
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MMMM-dd hh:mm:ss");
        System.out.println(simpleDateFormat2.format(date1)); //2021-三月-03 09:18:43
    }

    // Calendar
    @Test
    public void calendar_test(){
        // 实例化两种方法
        GregorianCalendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = Calendar.getInstance();
        // 方法测试
        System.out.println(calendar1.get(Calendar.DAY_OF_MONTH)); //3
        System.out.println(calendar2.get(Calendar.DAY_OF_WEEK)); //4 注：第一天为星期日
        // set()修改Calendar
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        System.out.println(calendar1.get(Calendar.DAY_OF_MONTH)); //1
        // add()修改Calendar
        calendar1.add(Calendar.DAY_OF_MONTH,1);
        System.out.println(calendar1.get(Calendar.DAY_OF_MONTH)); //2
        // Calendar->Date
        System.out.println(calendar2.getTime()); //Wed Mar 03 21:30:35 CST 2021
        // Date->Calendar
        calendar1.setTime(new Date());
        System.out.println(calendar1.get(Calendar.DAY_OF_MONTH)); //3
    }

    // Local_XXX
    @Test
    public void localTime_test(){
        // now() 实例化
        LocalDate localDate1 = LocalDate.now();
        LocalTime localTime1 = LocalTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDate1); //2021-03-03
        System.out.println(localTime1); //21:59:09.504
        System.out.println(localDateTime1); //2021-03-03T21:59:09.504
        // of() 指定时间实例化
        LocalDate localDate2 = LocalDate.of(2021, 3, 3);
        System.out.println(localDate2); //2021-03-03
        // getXXX() 获取指定时间信息
        System.out.println(localDate1.getYear()); //2021
        System.out.println(localTime1.getHour()); // 22
        System.out.println(localDateTime1.getDayOfMonth()); //3
    }

    @Test
    public void insant_test(){
        // now() 实例化
        Instant instant1 = Instant.now();
        // 本初子午线标准时间
        System.out.println(instant1); //2021-03-03T14:09:10.098Z
        // 时间戳 实例化
        Instant instant2 = Instant.ofEpochMilli(new Date().getTime());
        System.out.println(instant2); //2021-03-03T14:11:16.223Z
    }
}
