package com.onevgo.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BcMul {
    public static void main(String[] args) {
        System.out.println(new BigDecimal("1.34747474747").multiply(new BigDecimal("35")).setScale(3, RoundingMode.FLOOR));
        System.out.println(new BigDecimal("2").multiply(new BigDecimal("4")));
    }
}
