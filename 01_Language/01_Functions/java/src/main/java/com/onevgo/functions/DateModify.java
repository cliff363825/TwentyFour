package com.onevgo.functions;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateModify {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2006, 12, 12);
        date = date.plusDays(1);
        date = date.plus(Period.ofDays(1));
        date = date.plus(1L, ChronoUnit.DAYS);
        System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)));
    }
}
