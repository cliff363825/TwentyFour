package com.onevgo.functions;

public class Log1p {
    public static double log1p(double number) {
        return Math.log1p(number);
    }

    public static void main(String[] args) {
        System.out.println(log1p(0));
        System.out.println(log1p(1));
    }
}
