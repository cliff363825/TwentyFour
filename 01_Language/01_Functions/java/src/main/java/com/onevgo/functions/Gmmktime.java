package com.onevgo.functions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class Gmmktime {
    public static long gmmktime(int hour, int minute, int second, int month, int day, int year) {
        return LocalDateTime.of(year, month, day, hour, minute, second).toInstant(ZoneOffset.UTC).getEpochSecond();
    }

    public static void main(String[] args) {
        System.out.println(new Date(gmmktime(0, 0, 0, 7, 1, 2000) * 1000));
    }
}
