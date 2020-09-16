package com.onevgo.functions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimezoneGet {
    public static void main(String[] args) {
        System.out.println(new Date().getTimezoneOffset());

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTimeZone().getID());

        System.out.println(TimeZone.getDefault().getID());
    }
}
