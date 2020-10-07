package com.onevgo.functions;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class DateOffsetGet {
    public static void main(String[] args) throws ParseException {
        Date winter = DateUtils.parseDate("2010-12-21", "yyyy-MM-dd");
        Date summer = DateUtils.parseDate("2008-06-21", "yyyy-MM-dd");
        TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
        System.out.println(timeZone.getOffset(winter.getTime()));
        System.out.println(timeZone.getOffset(summer.getTime()));

        //jdk8+
        System.out.println(OffsetDateTime.ofInstant(winter.toInstant(), ZoneId.of("America/New_York")).getOffset().getTotalSeconds());
        System.out.println(OffsetDateTime.ofInstant(summer.toInstant(), ZoneId.of("America/New_York")).getOffset().getTotalSeconds());
    }
}
