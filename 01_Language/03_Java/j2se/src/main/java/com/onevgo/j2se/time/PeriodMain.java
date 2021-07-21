package com.onevgo.j2se.time;

import lombok.extern.slf4j.Slf4j;

import java.time.*;

@Slf4j
public class PeriodMain {
    public static void main(String[] args) {
//        testPeriod();
        testDaylightPeriod();
    }

    private static void testPeriod() {
        // Period：用于计算两个"日期"间隔，以年、月、日衡量
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2008, 8, 8);

        Period period = Period.between(localDate, localDate1);
        log.info("period = {}", period);
        log.info("{} years {} months {} days", period.getYears(), period.getMonths(), period.getDays());
        log.info("period.withYears(2) = {}", period.withYears(2));
    }

    private static void testDaylightPeriod() {
        LocalDateTime localDateTime = LocalDateTime.of(2013, 10, 27, 2, 30);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Berlin"));

        log.info("current = {}", zonedDateTime); // 2013-10-27T02:30+02:00[Europe/Berlin]
        log.info("+1 hour = {}", zonedDateTime.plusHours(1)); // 2013-10-27T02:30+01:00[Europe/Berlin]

        log.info("+7 days = {}", zonedDateTime.plusDays(7)); // 2013-11-03T02:30+01:00[Europe/Berlin]
        log.info("+7 days(Duration) = {}", zonedDateTime.plus(Duration.ofDays(7))); // 2013-11-03T01:30+01:00[Europe/Berlin]
        log.info("+7 days(Period) = {}", zonedDateTime.plus(Period.ofDays(7))); // 2013-11-03T02:30+01:00[Europe/Berlin]
    }
}
