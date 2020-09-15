package com.onevgo.functions;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Gmmktime {
    public static long gmmktime(int hour, int minute, int second, int month, int day, int year) {
        return OffsetDateTime.of(year, month, day, hour, minute, second, 0, ZoneOffset.UTC).toEpochSecond();
    }

    public static void main(String[] args) {
        System.out.println(gmmktime(0, 0, 0, 7, 1, 2000));
    }
}
