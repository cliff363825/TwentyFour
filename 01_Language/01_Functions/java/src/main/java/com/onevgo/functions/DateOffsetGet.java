package com.onevgo.functions;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class DateOffsetGet {
    public static void main(String[] args) {
        DateTime winter = DateUtil.parse("2010-12-21", "yyyy-MM-dd");
        DateTime summer = DateUtil.parse("2008-06-21", "yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        System.out.println(calendar.getTimeZone().getOffset(winter.getTime()));
        System.out.println(calendar.getTimeZone().getOffset(summer.getTime()));

        //jdk8+
        System.out.println(OffsetDateTime.ofInstant(winter.toInstant(), ZoneId.of("America/New_York")).getOffset().getTotalSeconds());
        System.out.println(OffsetDateTime.ofInstant(summer.toInstant(), ZoneId.of("America/New_York")).getOffset().getTotalSeconds());
    }
}
