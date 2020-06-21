package com.onevgo.spring.annotation.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {
    /**
     * ConditionContext 判断条件能使用的上下文环境
     * AnnotatedTypeMetadata 注释信息
     *
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 是否 Linux 系统
        // 1. 能获取到 ioc 使用的 beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 2. 获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 3. 获取当前环境信息
        Environment environment = context.getEnvironment();
        // 4. 获取到bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        String osName = environment.getProperty("os.name");
        if (osName != null && osName.contains("Mac OS X")) {
            return true;
        }
        return false;
    }
}
