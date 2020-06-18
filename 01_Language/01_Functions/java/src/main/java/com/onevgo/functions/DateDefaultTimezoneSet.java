package com.onevgo.functions;

import java.util.TimeZone;

public class DateDefaultTimezoneSet {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(timeZone);
    }
}
