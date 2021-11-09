package com.onevgo.j2se.design.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式 - 懒汉式
 * DCL(double-checked lock) + volatile
 */
@Slf4j
public class LazyThreadSafeSingleton {
    private static volatile LazyThreadSafeSingleton instance = null;

    private LazyThreadSafeSingleton() {
        log.info("LazyThreadSafeSingleton");
    }

    public static LazyThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyThreadSafeSingleton.class) {
                if (instance == null) {
                    // 这里分为3个语句执行
                    // lazyThreadSafeSingleton = allocate(); // 语句1 分配内存
                    // lazyThreadSafeSingleton.<init>(); // 语句2 初始化对象
                    // instance = lazyThreadSafeSingleton; // 语句3 将对象引用赋值给instance
                    // 由于语句2和语句3没有数据依赖性，因此可能会出现指令重排序，即执行顺序为1-3-2
                    // 在多线程下，当其他线程执行到 instance == null 时结果为 false，
                    // 则直接返回了未初始化完成的 instance，导致程序错误。
                    // 解决方法：使用 volatile 修饰 instance，禁止指令重排序。
                    instance = new LazyThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
