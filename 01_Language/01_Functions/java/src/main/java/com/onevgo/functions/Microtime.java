package com.onevgo.functions;

import java.time.Instant;

public class Microtime {
    public static String microtime() {
        Instant now = Instant.now();
        return String.format("%.8f %d", now.getNano() * 1.0 / 1000_000_000, now.getEpochSecond());
    }

    public static double microtime(boolean getAsFloat) {
        if (!getAsFloat) {
            throw new IllegalArgumentException("getAsFloat");
        }
        Instant now = Instant.now();
        return now.getEpochSecond() + now.getNano() * 1.0 / 1000_000_000;
    }

    public static void main(String[] args) {
        System.out.println(microtime());
        System.out.printf("%f", microtime(true));
    }
}
