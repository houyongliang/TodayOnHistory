package com.fnfh.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/18.
 */

public class DateUtil {
    public static int num=0;
    public static String getDateStr(String day,int Num) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
        Date newDate2 = new Date(nowDate.getTime() +(long)Num * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }
    public static String getNowDateStr() {
        Date newDate2 = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }



    public static String getMonth(String day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        Date nowDate = null;
        String month="";
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
//        Date newDate2 = new Date(nowDate.getTime() );
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        if(nowDate==null){
            month = simpleDateFormat.format(System.currentTimeMillis());
        }else{
            month = simpleDateFormat.format(nowDate);
        }


        return month;
    }
    public static String getMonthDate(String day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年M月d日");
        Date nowDate = null;
        String date;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
//        Date newDate2 = new Date(nowDate.getTime() );

        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd");
        if(nowDate==null){
            date = simpleDateFormat.format(System.currentTimeMillis());
        }else{
            date = simpleDateFormat.format(nowDate);
        }

        return date;
    }

}
