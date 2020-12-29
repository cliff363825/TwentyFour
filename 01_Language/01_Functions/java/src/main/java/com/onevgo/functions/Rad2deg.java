package com.onevgo.functions;

public class Rad2deg {
    public static double rad2deg(double number) {
        return Math.toDegrees(number);
    }

    public static void main(String[] args) {
        System.out.println(rad2deg(Math.PI / 4));
    }
}
