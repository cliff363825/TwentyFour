package com.onevgo.functions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateSub {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 20, 0, 0, 0);
        calendar.add(Calendar.DATE, -10);
        System.out.println(calendar.getTime());

        //jdk8+
        LocalDateTime dateTime = LocalDate.of(2000, 1, 20).atStartOfDay();
        dateTime = dateTime.minus(Period.ofDays(10));// P10D
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
