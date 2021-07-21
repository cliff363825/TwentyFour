package com.onevgo.j2se.time;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
public class DateMain {
    public static void main(String[] args) {
//        testDate();
//        testSimpleDateFormat();
//        testTimeZone();
//        testDateToInstant();
        testDateToLocalDateTime();
    }

    private static void testDate() {
        Date date = new Date();
        log.info("date={} time={}", date, date.getTime());

        long currentTimeMillis = System.currentTimeMillis();
        Date date1 = new Date(currentTimeMillis);
        log.info("date1={} date1.getTime()==currentTimeMillis?{}", date1, date1.getTime() == currentTimeMillis);

        Date date2 = new java.sql.Date(date.getTime());
        log.info("date2={}", date2);
    }

    private static void testSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("yyyy-MM-dd HH:mm:ss={}", simpleDateFormat.format(new Date()));

        try {
            log.info("date={}", simpleDateFormat.parse("2008-08-08 08:08:08"));
        } catch (ParseException e) {
            log.error("error=", e);
        }
    }

    private static void testTimeZone() {
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        log.info("CURRENT {}", simpleDateFormat.format(date));

        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-8")); // 西8时区
        log.info("GMT-8 {}", simpleDateFormat.format(date));
    }

    private static void testDateToInstant() {
        // Date -> Instant
        Date date = new Date();
        Instant instant = date.toInstant();
        log.info("date={} instant={}",date, instant);
    }

    private static void testDateToLocalDateTime() {
        // Date -> LocalDateTime(LocalDate+LocalTime)
        Date date = new Date();
        LocalDateTime localDateTime1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        log.info("{} {}", localDateTime1, localDateTime2);
    }
}
