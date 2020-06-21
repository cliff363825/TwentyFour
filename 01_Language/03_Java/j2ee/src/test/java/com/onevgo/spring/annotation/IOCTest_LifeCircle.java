package com.onevgo.spring.annotation;

import com.onevgo.spring.annotation.config.MainConfigOfLifeCircle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_LifeCircle {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCircle.class);
    }

    @After
    public void tearDown() throws Exception {
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    @Test
    public void test01() {
    }
}
