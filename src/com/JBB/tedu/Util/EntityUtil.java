package com.JBB.tedu.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class EntityUtil {
    //周数
    public static int weekNum = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

    //解析当前时间
    public static String parseTime() {
//        用时间的样式解析时间 格式为
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String time = df.format(new Date());
        return time;
    }

    public static String parseDay() {
//        用时间的样式解析时间 格式为
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        String time = df.format(new Date());
        return time;
    }

    public static int paseMonth() {
        String[] strs = parseTime().split("-");
        int mouth = Integer.parseInt(strs[1]);
        return mouth;
    }

    public static int paseYear() {
        String[] strs = parseTime().split("-");
        int year = Integer.parseInt(strs[0]);
        return year;
    }


    public static void main(String[] args) {
        String time = parseTime();
        System.out.println(time);
        System.out.println(paseMonth());
    }
}
