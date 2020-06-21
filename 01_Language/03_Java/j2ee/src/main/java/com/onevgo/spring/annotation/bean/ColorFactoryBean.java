package com.onevgo.spring.annotation.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 创建一个 Spring 定义的 FactoryBean
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    /**
     * 返回一个 Color 对象，这个对象会添加到容器中
     *
     * @return
     * @throws Exception
     */
    @Override
    public Color getObject() throws Exception {
//        System.out.println("ColorFactoryBean.getObject");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 是单例
     * 1. true 这个 bean 是单实例，在容器中保存一份
     * 2. false 多实例，每次获取都会创建一个新的实例
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
