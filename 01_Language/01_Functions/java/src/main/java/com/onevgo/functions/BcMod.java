package com.onevgo.functions;

import java.math.BigDecimal;

public class BcMod {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal("5");
        BigDecimal d2 = new BigDecimal("3");
        BigDecimal d3 = new BigDecimal("-5");
        BigDecimal d4 = new BigDecimal("-3");

        System.out.println(d1.remainder(d2));
        System.out.println(d1.remainder(d4));
        System.out.println(d3.remainder(d2));
        System.out.println(d3.remainder(d4));

        BigDecimal d5 = new BigDecimal("5.7");
        BigDecimal d6 = new BigDecimal("1.3");
        System.out.println(d5.remainder(d6));
    }
}
