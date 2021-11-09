package com.onevgo.j2se.design.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EagerSingleton1 {
    public final static EagerSingleton1 INSTANCE = new EagerSingleton1();

    private EagerSingleton1() {
        log.info("EagerSingleton1");
    }
}
