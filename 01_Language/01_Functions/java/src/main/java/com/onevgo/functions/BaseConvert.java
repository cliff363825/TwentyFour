package com.onevgo.functions;

public class BaseConvert {
    public static String baseConvert(String number, int frombase, int tobase) {
        if (frombase < 2 || frombase > 36 || tobase < 2 || tobase > 36) return null;
        try {
            return Integer.toString(Integer.parseInt(number, frombase), tobase);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(baseConvert("a37334", 16, 2));
    }
}
