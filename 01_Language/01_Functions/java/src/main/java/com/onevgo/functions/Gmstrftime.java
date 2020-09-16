package com.onevgo.functions;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Gmstrftime {
    public static String gmstrftime(String format, Long timestamp) {
        Instant instant;
        if (timestamp == null) {
            instant = Instant.now();
        } else {
            instant = Instant.ofEpochMilli(timestamp);
        }
        return instant.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(format));
    }

    public static void main(String[] args) {
        System.out.println(gmstrftime("yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1998, 12, 31, 20, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
    }
}
