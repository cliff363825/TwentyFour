package com.onevgo.spring.annotation;

import com.onevgo.spring.annotation.config.MainConfigOfProfile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Profile {
    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        // 1. 创建一个 applicationContext
        applicationContext = new AnnotationConfigApplicationContext();
        // 2. 设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test", "dev");
        // 3. 注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        // 4. 启动刷新容器
        applicationContext.refresh();
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    /**
     * 1. 使用命令行参数 在虚拟机参数位置加载 -Dspring.profiles.active=test
     * 2. 代码的方式激活某种环境
     */
    @Test
    public void test01() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }
}
