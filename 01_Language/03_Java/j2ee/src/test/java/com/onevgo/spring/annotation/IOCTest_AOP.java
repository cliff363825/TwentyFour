package com.onevgo.spring.annotation;

import com.onevgo.spring.annotation.aop.MathCalculator;
import com.onevgo.spring.annotation.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_AOP {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        // 1. 不要自己创建对象
//        MathCalculator mathCalculator = new MathCalculator();
//        System.out.println(mathCalculator.div(10, 2));

        MathCalculator mathCalculator = annotationConfigApplicationContext.getBean(MathCalculator.class);
        mathCalculator.div(10, 0);
    }
}
