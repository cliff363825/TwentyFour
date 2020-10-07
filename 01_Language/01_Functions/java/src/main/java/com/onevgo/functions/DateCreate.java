package com.onevgo.functions;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateCreate {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        System.out.println(DateFormatUtils.format(calendar, "yyyy-MM-dd"));

        //jdk8+
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
