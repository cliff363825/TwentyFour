package com.onevgo.spring.aop;

import com.onevgo.spring.aop.helloworld.ArithmeticCalculator;
import com.onevgo.spring.aop.helloworld.ArithmeticCalculatorImpl;
import com.onevgo.spring.aop.helloworld.ArithmeticCalculatorLoggingProxy;
import org.junit.Test;

public class AopTest {
    @Test
    public void test01() {
        ArithmeticCalculator target = new ArithmeticCalculatorImpl();
        ArithmeticCalculator proxy = new ArithmeticCalculatorLoggingProxy(target).getLoggingProxy();

        int result = proxy.add(1, 2);
        System.out.println("--> " + result);

        result = proxy.div(4, 2);
        System.out.println("--> " + result);
    }
}
