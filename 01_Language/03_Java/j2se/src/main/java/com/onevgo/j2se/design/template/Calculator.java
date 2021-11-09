package com.onevgo.j2se.design.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体实现类
 */
@Slf4j
public class Calculator extends ExampleTemplate {
    @Override
    protected void run() throws Throwable {
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            sum += i;
        }
        log.info("sum={}", sum);
    }
}
