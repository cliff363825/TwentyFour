package com.onevgo.functions;

import cn.hutool.core.date.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimezoneSet {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Pacific/Nauru"));
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        System.out.println(simpleDateFormat.format(calendar.getTime()));

        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Pacific/Chatham"));
        System.out.println(simpleDateFormat.format(calendar.getTime()));

        //jdk8+
        LocalDateTime localDateTime = LocalDate.of(2000, 1, 1).atStartOfDay();
        ZonedDateTime dateTime1 = localDateTime.atZone(ZoneId.of("Pacific/Nauru"));
        ZonedDateTime dateTime2 = dateTime1.withZoneSameInstant(ZoneId.of("Pacific/Chatham"));
        System.out.println(dateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX")));
        System.out.println(dateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX")));
    }
}
