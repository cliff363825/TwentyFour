package com.onevgo.j2se.design.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板类
 */
@Slf4j
public abstract class ExampleTemplate {
    public final void exec() {
        try {
            run();
        } catch (Throwable e) {
            log.error("error=", e);
        }
    }

    protected abstract void run() throws Throwable;
}
