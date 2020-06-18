package com.onevgo.functions;

import java.util.Calendar;

public class DateIsodateSet {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setWeekDate(2008, 2, Calendar.MONDAY);
        System.out.println(calendar.getTime());
        calendar.setWeekDate(2008, 2, Calendar.SUNDAY);
        System.out.println(calendar.getTime());
    }
}
