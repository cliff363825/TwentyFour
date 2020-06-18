package com.onevgo.functions;

import java.util.TimeZone;

public class DateDefaultTimezoneGet {
    public static void main(String[] args) {
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(timeZone);
    }
}
