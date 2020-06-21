package com.onevgo.spring.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Red.setApplicationContext: " + applicationContext.getClass().getName());
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Red.setBeanName: " + name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("Red.setEmbeddedValueResolver: " + resolver.getClass().getName());
        String str = "你好，${os.name}， 我是 #{18*20}";
        String resolveStringValue = resolver.resolveStringValue(str);
        System.out.println("Red.setEmbeddedValueResolver: " + str + " => " + resolveStringValue);
    }
}
