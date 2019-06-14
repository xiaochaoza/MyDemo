package com.fzzz.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-21
 * update:
 */
public class TimeUtil {

//        DateFormat dateFormat1 = SimpleDateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
//        DateFormat dateFormat2 = SimpleDateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
//        DateFormat dateFormat3 = SimpleDateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
//        DateFormat dateFormat4 = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
//        System.out.println(dateFormat1.format(new Date()));
//        System.out.println(dateFormat2.format(new Date()));
//        System.out.println(dateFormat3.format(new Date()));
//        System.out.println(dateFormat4.format(new Date()));
//        2019年5月22日星期三 中国标准时间 上午9:56:25
//        2019年5月22日 CST 上午9:56:25
//        2019年5月22日 上午9:56:25
//        2019/5/22 上午9:56
    public static String getLongTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return formatter.format(new Date());
    }

}
