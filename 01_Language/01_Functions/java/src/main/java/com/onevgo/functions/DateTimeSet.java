package com.onevgo.functions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeSet {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 55);
        System.out.println(calendar.getTime());

        String s = LocalDate.of(2001, 1, 1).atStartOfDay()
                .withHour(14)
                .withMinute(55)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH));
        System.out.println(s);
    }
}
