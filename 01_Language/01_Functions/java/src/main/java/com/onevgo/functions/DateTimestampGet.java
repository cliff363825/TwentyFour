package com.onevgo.functions;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimestampGet {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
