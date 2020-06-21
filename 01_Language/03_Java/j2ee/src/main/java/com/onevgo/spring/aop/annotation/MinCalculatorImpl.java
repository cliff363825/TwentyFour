package com.onevgo.spring.aop.annotation;

public class MinCalculatorImpl implements MinCalculator {
    public MinCalculatorImpl() {
        System.out.println("MinCalculatorImpl.MinCalculatorImpl");
    }

    @Override
    public int min(int x, int y) {
        return Math.min(x, y);
    }
}
