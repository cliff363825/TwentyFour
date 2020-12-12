package com.onevgo.functions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Mktime {
    public static long mktime(int hour, int minute, int second, int month, int day, int year) {
        return LocalDateTime.of(year, month, day, hour, minute, second).atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static void main(String[] args) {
        System.out.println(new Date(mktime(0, 0, 0, 1, 1, 2020) * 1000));
    }
}
