package com.onevgo.j2se.time;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Slf4j
public class InstantMain {
    public static void main(String[] args) {
//        testNow();
//        testInstantToLocalDateTime();
        testInstantToDate();
    }

    private static void testNow() {
        log.info("now = {}", Instant.now());
        log.info("now = {}", Instant.ofEpochMilli(System.currentTimeMillis()));
        log.info("now = {}", Instant.ofEpochSecond(System.currentTimeMillis() / 1000));
    }

    private static void testInstantToLocalDateTime() {
        Instant now = Instant.now();

        log.info("LocalDateTime.ofInstant = {}", LocalDateTime.ofInstant(now, ZoneId.systemDefault()));
        log.info("toLocalDateTime = {}", now.atZone(ZoneId.systemDefault()).toLocalDateTime());
        log.info("toLocalDateTime = {}", now.atOffset(ZoneOffset.of("+8")).toLocalDateTime());
    }

    private static void testInstantToDate() {
        // Instant -> Date
        log.info("{}", Date.from(Instant.now()));
    }
}
