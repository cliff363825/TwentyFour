package com.onevgo.functions;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class DateCreateFromFormat {
    public static void main(String[] args) {
        DateTime dateTime = DateUtil.parse("15-Feb-2009", "d-MMM-yyyy", Locale.ENGLISH);
        System.out.println(dateTime);

        //jdk8+
        LocalDate localDate = LocalDate.parse("15-Feb-2009", DateTimeFormatter.ofPattern("d-MMM-yyyy", Locale.ENGLISH));
        System.out.println(localDate);
    }
}
