package com.onevgo.functions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimezoneSet {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDate.of(2000, 1, 1).atStartOfDay();
        ZonedDateTime dateTime1 = dateTime.atZone(ZoneId.of("Pacific/Nauru"));
        ZonedDateTime dateTime2 = dateTime1.withZoneSameInstant(ZoneId.of("Pacific/Chatham"));
        System.out.println(dateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX")));
        System.out.println(dateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX")));
    }
}
