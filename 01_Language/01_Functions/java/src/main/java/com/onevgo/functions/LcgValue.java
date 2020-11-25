package com.onevgo.functions;

import java.util.Random;

public class LcgValue {
    public static double lcgValue() {
        return new Random().nextDouble();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(lcgValue());
        }
    }
}
