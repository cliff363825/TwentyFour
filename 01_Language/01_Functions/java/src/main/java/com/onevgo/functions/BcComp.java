package com.onevgo.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BcComp {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal("1");
        BigDecimal d2 = new BigDecimal("2");
        BigDecimal d3 = new BigDecimal("1.00001");
        System.out.println(d1.compareTo(d2));
        System.out.println(d3.setScale(3, RoundingMode.FLOOR).compareTo(d1));
        System.out.println(d3.setScale(5, RoundingMode.FLOOR).compareTo(d1));
    }
}
