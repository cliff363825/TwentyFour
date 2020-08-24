package com.onevgo.functions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class Getdate {
    public static Map<String, Object> getdate() {
        return getdate(System.currentTimeMillis());
    }

    public static Map<String, Object> getdate(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        HashMap<String, Object> map = new HashMap<>();
        map.put("seconds", localDateTime.getSecond());
        map.put("minutes", localDateTime.getMinute());
        map.put("hours", localDateTime.getHour());
        map.put("mday", localDateTime.getDayOfMonth());
        map.put("wday", localDateTime.getDayOfWeek().getValue());
        map.put("mon", localDateTime.getMonthValue());
        map.put("year", localDateTime.getYear());
        map.put("yday", localDateTime.getDayOfYear() - 1);
        map.put("weekday", localDateTime.getDayOfWeek().toString());
        map.put("month", localDateTime.getMonth().toString());
        map.put("0", instant.getEpochSecond());
        return map;
    }

    public static void main(String[] args) {
        System.out.println(getdate());
    }
}
