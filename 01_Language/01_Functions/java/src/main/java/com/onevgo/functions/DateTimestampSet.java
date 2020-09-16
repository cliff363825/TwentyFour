package com.onevgo.functions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimestampSet {
    public static void main(String[] args) {
        System.out.println(new Date(1171502725000L));

        //jdk8+
        System.out.println(Instant.ofEpochSecond(1171502725).atZone(ZoneId.systemDefault()));
    }
}
