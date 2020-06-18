package com.onevgo.functions;

import java.util.Calendar;

public class CheckDate {
    public static boolean checkDate(int month, int day, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        try {
            calendar.set(year, month - 1, day, 0, 0, 0);
            calendar.getTime();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(checkDate(12, 31, 2000));
        System.out.println(checkDate(2, 29, 2001));
    }
}
