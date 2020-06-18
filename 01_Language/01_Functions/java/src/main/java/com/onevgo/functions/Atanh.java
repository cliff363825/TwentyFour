package com.onevgo.functions;

public class Atanh {
    public static double atanh(double x) {
        return 0.5 * Math.log((1.0 + x) / (1.0 - x));
    }

    public static void main(String[] args) {
        System.out.println(atanh(Math.tanh(2)));
    }
}
