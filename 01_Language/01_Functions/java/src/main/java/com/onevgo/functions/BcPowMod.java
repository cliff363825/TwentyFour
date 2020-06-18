package com.onevgo.functions;

import java.math.BigDecimal;

public class BcPowMod {
    public static void main(String[] args) {
        System.out.println(new BigDecimal("10").pow(2).remainder(new BigDecimal("3")));
    }
}
