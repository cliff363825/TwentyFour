package com.onevgo.functions;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateParse {
    public static void main(String[] args) throws ParseException {
        Date dateTime = DateUtils.parseDate("2006-12-12 10:00:00.5", Locale.ENGLISH, "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(dateTime);

        //jdk8+
        System.out.println(LocalDateTime.parse("2006-12-12 10:00:00.5", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
    }
}
