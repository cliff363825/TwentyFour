package com.onevgo.functions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DateDiff {
    public static void main(String[] args) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2009, Calendar.OCTOBER, 11, 0, 0, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2009, Calendar.OCTOBER, 13, 0, 0, 0);
        System.out.println((calendar1.getTime().getTime() - calendar2.getTime().getTime()) / 1000 / 60 / 60 / 24);

        //jdk8+
        LocalDateTime dateTime1 = LocalDateTime.of(2009, 10, 11, 0, 0, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2009, 10, 13, 0, 0, 0);
        Duration duration = Duration.between(dateTime1, dateTime2);
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.getSeconds());
    }
}
