package com.onevgo.functions;

public class IsFinite {
    public static void main(String[] args) {
        System.out.println(Double.isFinite(Math.sqrt(2)));
        System.out.println(Double.isFinite(Math.log(0)));
        System.out.println(Double.isFinite(Math.asin(2)));
    }
}
