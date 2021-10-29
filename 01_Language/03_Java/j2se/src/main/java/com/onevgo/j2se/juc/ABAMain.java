package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class ABAMain {
    public static void main(String[] args) {
//        testABA();
        testABAResolve();
    }

    private static void testABA() {
        log.info("以下是ABA问题的产生");

        AtomicReference<String> atomicReference = new AtomicReference<>("A");

        Thread t1 = new Thread(() -> {
            atomicReference.compareAndSet("A", "B"); // A -> B
            atomicReference.compareAndSet("B", "A"); // B -> A
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                // t2线程暂停1秒钟，保证上面的t1线程完成了一次ABA操作
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error("error=", e);
            }
            boolean b = atomicReference.compareAndSet("A", "C"); // A -> C
            log.info("{} {}", b, atomicReference.get());
        }, "t2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            log.error("error=", e);
        }
    }

    private static void testABAResolve() {
        log.info("以下是ABA问题的解决");

        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("A", 1);

        Thread t3 = new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            log.info("第一次版本号={}", stamp); // 1
            // t3线程暂停1秒钟
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error("error=", e);
            }
            atomicStampedReference.compareAndSet("A", "B", stamp, stamp + 1); // A -> B, 1 -> 2
            log.info("第二次版本号={}", atomicStampedReference.getStamp()); // 2

            atomicStampedReference.compareAndSet("B", "A", atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1); // B -> A, 2 -> 3
            log.info("第三次版本号={}", atomicStampedReference.getStamp()); // 3
        }, "t3");

        Thread t4 = new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            log.info("第一次版本号={}", stamp); // 1
            // t4线程暂停3秒钟，保证上面t3线程完成了一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean b = atomicStampedReference.compareAndSet("A", "C", stamp, stamp + 1); // A -> C, 1 -> 2 => false
            log.info("{} {} {}", b, atomicStampedReference.getReference(), atomicStampedReference.getStamp());
        }, "t4");

        t3.start();
        t4.start();

        try {
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            log.error("error=", e);
        }
    }
}
