package com.onevgo.functions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeSet {
    public static void main(String[] args) {
        String s = LocalDate.of(2001, 1, 1).atStartOfDay()
                .withHour(14)
                .withMinute(55)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH));
        System.out.println(s);
    }
}
