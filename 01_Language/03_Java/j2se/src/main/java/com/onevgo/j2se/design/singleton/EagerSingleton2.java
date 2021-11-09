package com.onevgo.j2se.design.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EagerSingleton2 {
    public final static EagerSingleton2 INSTANCE;

    static {
        INSTANCE = new EagerSingleton2();
    }

    private EagerSingleton2() {
        log.info("EagerSingleton2");
    }
}
