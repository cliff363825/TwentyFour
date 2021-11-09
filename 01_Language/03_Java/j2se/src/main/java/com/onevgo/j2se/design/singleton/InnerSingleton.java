package com.onevgo.j2se.design.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是要单独去加载和初始化的。
 * 因为是在内部类加载和初始化时创建的，因此是线程安全的。
 */
@Slf4j
public class InnerSingleton {
    private InnerSingleton() {
        log.info("InnerSingleton");
    }

    public static InnerSingleton getInstance() {
        return Example.INSTANCE;
    }

    private static class Example {
        private final static InnerSingleton INSTANCE = new InnerSingleton();
    }
}
