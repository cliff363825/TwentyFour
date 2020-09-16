package com.onevgo.functions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFunc {
    public static String date(String format) {
        return date(format, new Date());
    }

    public static String date(String format, long timestamp) {
        return date(format, new Date(timestamp));
    }

    public static String date(String format, Date d) {
        return date(format, d.toInstant().atZone(ZoneId.systemDefault()));
    }

    public static String date(String format, Calendar calendar) {
        return date(format, calendar.getTime());
    }

    public static String date(String format, TemporalAccessor temporal) {
        return DateTimeFormatter.ofPattern(format, Locale.ENGLISH).format(temporal);
    }

    public static void main(String[] args) {
        // set the default timezone to use. Available since PHP 5.1
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        // Prints something like: Monday
        System.out.println(date("EEEE"));

        // Prints something like: Monday 8th of August 2005 03:12:46 PM
        System.out.println(date("EEEE d MMMM yyyy hh:mm:ss a"));

        // Prints: July 1, 2000 is on a Saturday
        Temporal localDateTime = LocalDateTime.of(2000, 7, 1, 0, 0, 0)
                .atZone(ZoneId.systemDefault());
        System.out.println("July 1, 2000 is on a " + date("EEEE", localDateTime));

        /* use the constants in the format parameter */
        // prints something like: Wed, 25 Sep 2013 15:28:57 -0700
        System.out.println(date("EEE, dd MMM yyyy HH:mm:ss Z"));

        // prints something like: 2000-07-01T00:00:00+00:00
        System.out.println(date("yyyy-MM-dd'T'HH:mm:ssxxx", localDateTime));
    }
}
