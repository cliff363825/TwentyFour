package com.onevgo.spring.annotation;

import com.onevgo.spring.annotation.service.UserService;
import com.onevgo.spring.annotation.tx.TxConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Tx {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = applicationContext.getBean("myUserService", UserService.class);
        userService.insertUser();
    }
}
