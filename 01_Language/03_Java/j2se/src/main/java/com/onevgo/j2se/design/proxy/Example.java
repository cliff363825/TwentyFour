package com.onevgo.j2se.design.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 被代理类
 */
@Slf4j
public class Example implements IExample {
    @Override
    public void run() {
        log.info("被代理对象开始执行。。。");
    }
}
