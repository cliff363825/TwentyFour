package com.onevgo.functions;

public class IsInfinite {
    public static void main(String[] args) {
        System.out.println(Double.isInfinite(Math.sqrt(2)));
        System.out.println(Double.isInfinite(Math.log(0)));
        System.out.println(Double.isInfinite(Math.asin(2)));
    }
}
