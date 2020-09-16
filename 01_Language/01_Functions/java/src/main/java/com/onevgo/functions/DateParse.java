package com.onevgo.functions;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateParse {
    public static void main(String[] args) {
        DateTime dateTime = DateUtil.parse("2006-12-12 10:00:00.5", "yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        System.out.println(dateTime);
        System.out.println(dateTime.getTime());

        //jdk8+
        System.out.println(LocalDateTime.parse("2006-12-12 10:00:00.5", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
    }
}
