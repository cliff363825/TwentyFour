package com.onevgo.functions;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimezoneGet {
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().atZone(ZoneId.of("Europe/London")).getZone().getId());
    }
}
