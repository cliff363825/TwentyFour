package com.onevgo.j2se.time;

import lombok.extern.slf4j.Slf4j;

import java.time.*;

@Slf4j
public class DurationMain {
    public static void main(String[] args) {
//        testDuration();
        testDaylightDuration();
    }

    private static void testDuration() {
        // Duration：用于计算两个"时间"间隔，以秒和纳秒为基准
        LocalTime now = LocalTime.now();
        LocalTime end = LocalTime.MAX;

        // between()：静态方法，返回Duration对象，表示两个时间的间隔
        Duration duration = Duration.between(now, end);
        log.info("距离今天结束还有 {} 秒", duration.abs().getSeconds());
    }

    private static void testDaylightDuration() {
        LocalDateTime localDateTime = LocalDateTime.of(2013, 10, 27, 2, 30);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Berlin"));

        log.info("current = {}", zonedDateTime); // 2013-10-27T02:30+02:00[Europe/Berlin]
        log.info("+1 hour = {}", zonedDateTime.plusHours(1)); // 2013-10-27T02:30+01:00[Europe/Berlin]
        log.info("+7 days = {}", zonedDateTime.plusDays(7));
    }
}
