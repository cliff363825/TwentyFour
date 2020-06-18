package com.onevgo.functions;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Calendar;
import java.util.TimeZone;

public class DateOffsetGet {
    public static void main(String[] args) {
        DateTime winter = DateUtil.parse("2010-12-21", "yyyy-MM-dd");
        DateTime summer = DateUtil.parse("2008-06-21", "yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        System.out.println(calendar.getTimeZone().getOffset(winter.getTime()));
        System.out.println(calendar.getTimeZone().getOffset(summer.getTime()));
    }
}
