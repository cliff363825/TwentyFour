package com.onevgo.functions;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateDiff {
    public static void main(String[] args) {
        LocalDateTime dateTime1 = LocalDateTime.of(2009, 10, 11, 0, 0, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2009, 10, 13, 0, 0, 0);
        Duration duration = Duration.between(dateTime1, dateTime2);
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.getSeconds());
    }
}
