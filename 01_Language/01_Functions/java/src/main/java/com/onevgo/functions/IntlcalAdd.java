package com.onevgo.functions;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class IntlcalAdd {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2012, Calendar.JANUARY, 31);
        System.out.println(DateFormatUtils.format(calendar, "yyyy-MM-dd HH:mm:ss Z"));

        calendar.add(Calendar.MONTH, 1);
        System.out.println(DateFormatUtils.format(calendar, "yyyy-MM-dd HH:mm:ss Z"));

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(DateFormatUtils.format(calendar, "yyyy-MM-dd HH:mm:ss Z"));
    }
}
