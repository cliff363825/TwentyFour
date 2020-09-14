package com.onevgo.functions;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Gmdate {
    public static String gmdate(String format, long epochMilli) {
        Instant instant = Instant.ofEpochMilli(epochMilli);
        return instant.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(format));
    }

    public static String gmdate(String format) {
        return gmdate(format, Instant.now().toEpochMilli());
    }

    public static void main(String[] args) {
        System.out.println(DateFunc.date("yyyy-MM-dd HH:mm:ss"));
        System.out.println(gmdate("yyyy-MM-dd HH:mm:ss"));
    }
}
