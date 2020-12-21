package com.onevgo.functions;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class Localtime {
    public static Map<String, Integer> localtime() {
        return localtime(System.currentTimeMillis());
    }

    public static Map<String, Integer> localtime(long milliseconds) {
        Instant instant = Instant.ofEpochMilli(milliseconds);
        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
        HashMap<String, Integer> map = new HashMap<>();
        map.put("tm_sec", dateTime.getSecond());
        map.put("tm_min", dateTime.getMinute());
        map.put("tm_hour", dateTime.getHour());
        map.put("tm_mday", dateTime.getDayOfMonth());
        map.put("tm_mon", dateTime.getMonthValue());
        map.put("tm_year", dateTime.getYear());
        map.put("tm_wday", dateTime.getDayOfWeek().getValue());
        map.put("tm_yday", dateTime.getDayOfYear());
        //map.put("tm_isdst", 0);
        return map;
    }

    public static void main(String[] args) {
        System.out.println(localtime());
    }
}
