package com.chemguan.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Date now = new Date();
        System.out.println("当前日期：" + DATE_FORMAT.format(now));
        Date newDate = stepMonth(1);
        System.out.println("当前时间前13个月的日期：" + DATE_FORMAT.format(newDate));
    }

    /**
     * 在给定的日期加上或减去指定月份后的日期
     *
     * @param
     * @param month      要调整的月份，向前为负数，向后为正数
     * @return
     */
    public static Date stepMonth(int month) {

        Date sourceDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);

        return c.getTime();
    }
}
