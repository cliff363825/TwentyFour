package com.onevgo.spring.annotation.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {
    public Dog() {
        System.out.println("Dog.Dog");
    }

    /**
     * 对象创建并赋值之后使用
     */
    @PostConstruct
    public void init() {
        System.out.println("Dog.init");
    }

    /**
     * 容器移除对象之前
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Dog.destroy");
    }
}
