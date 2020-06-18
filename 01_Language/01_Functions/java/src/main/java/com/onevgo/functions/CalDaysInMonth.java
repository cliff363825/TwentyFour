package com.onevgo.functions;

import java.util.Calendar;

public class CalDaysInMonth {
    public static int calDaysInMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) {
        System.out.println(calDaysInMonth(8, 2003));
    }
}
