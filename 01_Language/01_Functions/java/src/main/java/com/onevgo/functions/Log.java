package com.onevgo.functions;

public class Log {
    public static double log(double arg) {
        return Math.log(arg);
    }

    public static double log(double arg, double base) {
        return Math.log(arg) / Math.log(base);
    }

    public static void main(String[] args) {
        System.out.println(log(1));
        System.out.println(log(2, 2));
    }
}
