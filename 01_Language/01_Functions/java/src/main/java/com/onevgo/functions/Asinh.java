package com.onevgo.functions;

public class Asinh {
    public static double asinh(double x) {
        return Math.log(x + Math.sqrt(x * x + 1.0));
    }

    public static void main(String[] args) {
        System.out.println(asinh(Math.sinh(5)));
    }
}
