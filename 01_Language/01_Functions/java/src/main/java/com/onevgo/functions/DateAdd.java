package com.onevgo.functions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAdd {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        localDateTime = localDateTime.plus(Duration.ofDays(10));
//        localDateTime = localDateTime.plusDays(10);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
