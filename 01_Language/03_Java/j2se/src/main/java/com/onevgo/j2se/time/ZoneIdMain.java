package com.onevgo.j2se.time;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

@Slf4j
public class ZoneIdMain {
    public static void main(String[] args) {
//        testAvailableZoneIds();
//        testZoneId();
        testZonedDateTime();
    }

    private static void testAvailableZoneIds() {
        // ZoneId 的 getAvailableZoneIds()：获取所有的ZoneId
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        log.info("{}", availableZoneIds);
    }

    private static void testZoneId() {
        // ZoneId的of()：获取指定时区的时间
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        log.info("Tokyo now = {}", now);
    }

    private static void testZonedDateTime() {
        // ZonedDateTime的now()：获取本时区的ZonedDateTime对象
        ZonedDateTime now = ZonedDateTime.now();
        log.info("now = {}", now);

        // ZonedDateTime的now(ZoneId id)：获取指定时区的ZonedDateTime对象
        ZonedDateTime now1 = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        log.info("Tokyo now = {}", now1);
    }
}
