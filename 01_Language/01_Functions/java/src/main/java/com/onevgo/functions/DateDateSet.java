package com.onevgo.functions;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class DateDateSet {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.FEBRUARY, 3);
        String s = calendar.toInstant().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        System.out.println(s);
    }
}
