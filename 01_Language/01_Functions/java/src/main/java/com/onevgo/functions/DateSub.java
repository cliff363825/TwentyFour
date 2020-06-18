package com.onevgo.functions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateSub {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDate.of(2000, 1, 20).atStartOfDay();
        dateTime = dateTime.minus(Period.ofDays(10));// P10D
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
