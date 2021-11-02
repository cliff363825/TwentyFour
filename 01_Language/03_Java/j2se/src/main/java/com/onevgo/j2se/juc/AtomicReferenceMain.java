package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicReferenceMain {
    public static void main(String[] args) {
        String zs = "zhangsan";
        String ls = "lisi";

        AtomicReference<String> atomicReference = new AtomicReference<>();
        atomicReference.set(zs);

        boolean b = atomicReference.compareAndSet(zs, ls);
        log.info("{} {}", b, atomicReference.get());

        boolean b1 = atomicReference.compareAndSet(zs, ls);
        log.info("{} {}", b1, atomicReference.get());
    }

}
