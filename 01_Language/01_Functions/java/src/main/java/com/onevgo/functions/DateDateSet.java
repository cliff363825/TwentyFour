package com.onevgo.functions;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class DateDateSet {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.FEBRUARY, 3, 0, 0, 0);
        System.out.println(DateFormatUtils.format(calendar, "yyyy-MM-dd"));

        //jdk8+
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.withYear(2001).withMonth(2).withDayOfMonth(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)));
    }
}
