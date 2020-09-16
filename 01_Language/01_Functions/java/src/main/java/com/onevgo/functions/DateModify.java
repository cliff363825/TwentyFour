package com.onevgo.functions;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

public class DateModify {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2006, Calendar.DECEMBER, 12, 0, 0, 0);
        calendar.add(Calendar.DATE, 1);
        System.out.println(DateUtil.format(calendar.getTime(), "yyyy-MM-dd"));

        //jdk8+
        LocalDate date = LocalDate.of(2006, 12, 12);
        date = date.plusDays(1);
        date = date.plus(Period.ofDays(1));
        date = date.plus(1L, ChronoUnit.DAYS);
        System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)));
    }
}
