package com.onevgo.j2se.design.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer extends Example {
    @Override
    public void run() {
        log.info("执行消费...");
    }
}
