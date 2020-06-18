package com.onevgo.functions;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateCreateFromFormat {
    public static void main(String[] args) {
        LocalDate dateTime = LocalDate.parse("15-Feb-2009", DateTimeFormatter.ofPattern("d-MMM-yyyy", Locale.ENGLISH));
        System.out.println(dateTime);

        DateTime dateTime1 = DateUtil.parse("15-Feb-2009", "d-MMM-yyyy", Locale.ENGLISH);
        System.out.println(dateTime1);
    }
}
