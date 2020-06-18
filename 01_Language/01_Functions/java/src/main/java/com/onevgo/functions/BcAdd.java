package com.onevgo.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BcAdd {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal("1.2345");
        BigDecimal d2 = new BigDecimal("5");
        System.out.println(d1.add(d2).toString());
        System.out.println(d1.add(d2).setScale(3, RoundingMode.FLOOR).toString());
    }
}
