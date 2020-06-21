package com.onevgo.spring.aop;

import com.onevgo.spring.aop.xml.ArithmeticCalculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest_Xml {
    @Test
    public void test01() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop-xml.xml");
        ArithmeticCalculator arithmeticCalculator = applicationContext.getBean("arithmeticCalculator", ArithmeticCalculator.class);

        int result = arithmeticCalculator.add(3, 6);
        System.out.println("result: " + result);

//        result = arithmeticCalculator.div(12, 0);
//        System.out.println("result: " + result);

        result = arithmeticCalculator.div(12, 2);
        System.out.println("result: " + result);
    }
}
