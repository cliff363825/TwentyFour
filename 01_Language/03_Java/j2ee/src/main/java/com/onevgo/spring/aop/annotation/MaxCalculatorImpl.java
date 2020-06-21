package com.onevgo.spring.aop.annotation;

public class MaxCalculatorImpl implements MaxCalculator {
    public MaxCalculatorImpl() {
        System.out.println("MaxCalculatorImpl.MaxCalculatorImpl");
    }

    @Override
    public int max(int x, int y) {
        return Math.max(x, y);
    }
}
