package com.onevgo.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BcPow {
    public static void main(String[] args) {
        System.out.println(new BigDecimal("4.2").pow(3).setScale(2, RoundingMode.FLOOR));
    }
}
