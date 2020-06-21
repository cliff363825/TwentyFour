package com.onevgo.spring.annotation;

import com.onevgo.spring.annotation.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Ext {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        applicationContext.publishEvent(new ApplicationEvent("我发布事件") {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });
        applicationContext.close();
    }
}
