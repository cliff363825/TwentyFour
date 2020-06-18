package com.onevgo.functions;

import java.time.Duration;

public class DateIntervalCreateFromDateString {
    public static void main(String[] args) {
        System.out.println(Duration.parse("P1D"));
//        System.out.println(Duration.parse("P2W"));
//        System.out.println(Duration.parse("P3M"));
//        System.out.println(Duration.parse("P4Y"));
        System.out.println(Duration.parse("P1DT12H"));
        System.out.println(Duration.parse("PT3600S"));
    }
}
