package com.onevgo.functions;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateAdd {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        System.out.println(DateFormatUtils.format(calendar, "yyyy-MM-dd"));

        // jdk8+
        LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        localDateTime = localDateTime.plus(Duration.ofDays(10));
//        localDateTime = localDateTime.plusDays(10);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
