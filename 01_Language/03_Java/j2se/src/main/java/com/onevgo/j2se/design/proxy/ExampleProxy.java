package com.onevgo.j2se.design.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理类
 */
@Slf4j
public class ExampleProxy implements IExample {
    /**
     * 被代理对象
     */
    private final IExample target;

    public ExampleProxy(IExample target) {
        this.target = target;
    }

    @Override
    public void run() {
        log.info("执行开始时间={}", System.currentTimeMillis());
        target.run();
        log.info("执行结束时间={}", System.currentTimeMillis());
    }
}
