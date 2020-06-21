package juc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. CAS是什么？Compare And Swap
 * 比较并交换
 */
public class TestCAS {
    @Test
    public void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + ": " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + ": " + atomicInteger.get());
    }
}
