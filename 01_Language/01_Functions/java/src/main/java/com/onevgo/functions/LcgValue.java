package com.onevgo.functions;

import java.util.concurrent.ThreadLocalRandom;

public class LcgValue {
    public static double lcgValue() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(lcgValue());
        }
    }
}
