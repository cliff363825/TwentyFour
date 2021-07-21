package com.onevgo.j2se.time;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class TemporalAdjusterMain {
    public static void main(String[] args) {
//        testNext();
        testNextWorkDay();
    }

    private static void testNext() {
        // TemporalAdjuster：时间校正器
        // 获取当前日期的下一个周日是哪天？
        LocalDate next = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        log.info("下一个周日 = {}", next);

        LocalDate previous = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        log.info("上一个周日 = {}", previous);

        LocalDate firstDayOfNextMonth = LocalDate.of(2021, 1, 31).with(TemporalAdjusters.firstDayOfNextMonth());
        log.info("下个月的第一天 = {}", firstDayOfNextMonth);
    }

    private static void testNextWorkDay() {
        // 获取下一个工作日是哪天？
        TemporalAdjuster nextWorkDayAdjuster = temporal -> {
            do {
                temporal = temporal.plus(Period.ofDays(1));
            } while (temporal.get(ChronoField.DAY_OF_WEEK) >= DayOfWeek.SATURDAY.getValue());
            return temporal;
        };

        LocalDate nextWorkDay = LocalDate.now().with(nextWorkDayAdjuster);
        log.info("下一个工作日是 = {}", nextWorkDay);
        log.info("下一个工作日是 = {}", LocalDate.of(2021, 7, 23).with(nextWorkDayAdjuster));
    }
}
