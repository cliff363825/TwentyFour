package com.onevgo.functions;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateCreate {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        System.out.println(DateUtil.format(calendar.getTime(), "yyyy-MM-dd"));

        //jdk8+
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
