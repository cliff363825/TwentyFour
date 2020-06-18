package com.onevgo.functions;

import java.math.BigDecimal;

public class BcDiv {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal("105");
        BigDecimal d2 = new BigDecimal("6.55957");
        System.out.println(d1.divide(d2, 3, BigDecimal.ROUND_FLOOR));
    }
}
