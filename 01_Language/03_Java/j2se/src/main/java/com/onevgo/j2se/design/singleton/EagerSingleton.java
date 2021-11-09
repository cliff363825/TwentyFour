package com.onevgo.j2se.design.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉式：在类初始化时直接创建实例对象，不管你是否需要这个对象都会创建
 * <p>
 * 1. 构造器私有化
 * 2. 自行创建，并且用静态变量保存
 * 3. 向外提供这个实例
 * 4. 强调这是一个单例，我们可以用final修饰
 */
@Slf4j
public class EagerSingleton {
    private final static EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        log.info("EagerSingleton");
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
