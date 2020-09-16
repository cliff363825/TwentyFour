package com.onevgo.functions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimestampGet {
    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());

        //jdk8+
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
