package com.onevgo.j2se.time;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class CalendarMain {
    public static void main(String[] args) {
//        testAdd();
        testNextMonth();
    }

    private static void testAdd() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, 8);
        log.info("当前时间日设置为8后，时间是：{}", calendar.getTime());

        calendar.add(Calendar.HOUR, 2);
        log.info("当前时间加2小时后，时间是：{}", calendar.getTime());

        calendar.add(Calendar.MONTH, -2);
        log.info("当前日期减2个月后，时间是：{}", calendar.getTime());
    }

    private static void testNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.JANUARY, 31);
        // 2019-01-31 10:50:39
        log.info("current {}", String.format("%tF %<tT", calendar.getTime()));

        calendar.add(Calendar.MONTH, 1);
        // 2019-02-28 10:50:39
        log.info("+1 month {}", String.format("%tF %<tT", calendar.getTime()));
    }
}
