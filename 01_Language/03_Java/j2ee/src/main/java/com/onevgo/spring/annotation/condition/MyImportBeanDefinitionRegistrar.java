package com.onevgo.spring.annotation.condition;

import com.onevgo.spring.annotation.bean.Blue;
import com.onevgo.spring.annotation.bean.Rainbow;
import com.onevgo.spring.annotation.bean.Red;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * AnnotationMetadata 当前类的注解信息
     * BeanDefinitionRegistry BeanDefinition注册类
     * 1. 把所有需要添加到容器中的 bean, 调用 BeanDefinitionRegistry.registerBeanDefinition 手工注册进来
     *
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean definition = registry.containsBeanDefinition(Blue.class.getName());
        boolean definition2 = registry.containsBeanDefinition(Red.class.getName());

        if (definition && definition2) {
            // 指定 bean 定义信息 (Bean 的类型...)
            BeanDefinition beanDefinition = new RootBeanDefinition(Rainbow.class);
            // 注册一个 bean, 指定 bean 名
            registry.registerBeanDefinition("rainbow", beanDefinition);
        }
    }
}
