package com.onevgo.functions;

public class Acosh {
    public static double acosh(double x) {
        return Math.log(x + Math.sqrt(x * x - 1));
    }

    public static void main(String[] args) {
        System.out.println(acosh(1));
        System.out.println(acosh(1.000000000000001));
        System.out.println(acosh(2));
        System.out.println(acosh(3));
    }
}
