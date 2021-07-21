package com.onevgo.j2se.time;

import lombok.extern.slf4j.Slf4j;

import java.text.Format;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class LocalDateTimeMain {
    public static void main(String[] args) {
//        testNow();
//        testLocalDateTime();
//        testNextMonth();
//        testPrevMonth();
//        testDateTimeFormatter();
//        testDateTimeFormatterToFormat();
        testLocalDateTimeToDate();
    }

    private static void testNow() {
        log.info("now = {}", LocalDateTime.now());
        log.info("GMT now = {}", LocalDateTime.now(ZoneId.of("GMT")));
        log.info("Asia/Shanghai now = {}", LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
    }

    private static void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2008, 8, 8), LocalTime.of(8, 8, 8));
        log.info("{}", localDateTime);

        int dayOfYear = localDateTime.getDayOfYear();
        int dayOfMonth = localDateTime.getDayOfMonth();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();

        int year = localDateTime.getYear();
        Month month = localDateTime.getMonth();
        int monthValue = localDateTime.getMonthValue();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        log.info("{}-{}-{} {}:{}:{} 第{}天，星期{}", year, monthValue, dayOfMonth, hour, minute, second, dayOfYear, dayOfWeek.getValue());
    }

    private static void testNextMonth() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2021, 1, 31), LocalTime.MIN);
        // localDateTime = 2019-02-28T17:33:45.168
        log.info("localDateTime.plusMonths(1) = {}", localDateTime.plusMonths(1));
    }

    private static void testPrevMonth() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2021, 3, 31), LocalTime.MIN);
        // localDateTime1 = 2019-02-28T17:33:45.168
        log.info("localDateTime.minusMonths(1) = {}", localDateTime.minusMonths(1));
    }

    private static void testDateTimeFormatter() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("yyyy-MM-dd HH:mm:ss = {}", dateTimeFormatter.format(LocalDateTime.now()));
        log.info("LocalDateTime.parse = {}", LocalDateTime.parse("2019-12-09 17:46:49", dateTimeFormatter));
    }

    private static void testDateTimeFormatterToFormat() {
        Format format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").toFormat();
        log.info("{}", format.format(LocalDateTime.now()));
    }

    private static void testLocalDateTimeToDate() {
        LocalDateTime now = LocalDateTime.now();

        // LocalDateTime -> Date
        Date date = Date.from(now.toInstant(ZoneOffset.of("+8")));
        log.info("Date.from = {}", date);

        Date date1 = Date.from(now.atOffset(ZoneOffset.of("+8")).toInstant());
        log.info("Date.from = {}", date1);

        Date date2 = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        log.info("Date.from = {}", date2);

        // LocalDate -> LocalDateTime -> Date
        LocalDate now1 = LocalDate.now();
        Date date3 = Date.from(now1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        log.info("Date.from = {}", date3);
    }
}
