package com.onevgo.j2se.design.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉式：延迟创建这个实例对象
 * <p>
 * 1. 构造器私有化
 * 2. 用一个静态变量保存这个唯一的实例
 * 3. 提供一个静态方法，获取这个实例对象
 */
@Slf4j
public class LazyThreadUnsafeSingleton2 {
    private static volatile LazyThreadUnsafeSingleton2 instance = null;

    private LazyThreadUnsafeSingleton2() {
        log.info("LazyThreadUnsafeSingleton2");
    }

    public static LazyThreadUnsafeSingleton2 getInstance() {
        // 存在线程安全问题
        if (instance == null) {
            instance = new LazyThreadUnsafeSingleton2();
        }
        return instance;
    }
}
