package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. CAS是什么？Compare And Swap
 * 比较并交换
 */
@Slf4j
public class CASMain {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        log.info("{} {}", atomicInteger.compareAndSet(5, 2021), atomicInteger.get());
        log.info("{} {}", atomicInteger.compareAndSet(5, 1024), atomicInteger.get());
    }
}
