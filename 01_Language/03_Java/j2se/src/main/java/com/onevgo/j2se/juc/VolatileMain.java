package com.onevgo.j2se.juc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. 验证volatile的可见性
 * 1.1 假如 int number = 0; number 变量之前根本没有添加 volatile 关键字修饰，没有可见性。
 * 1.2 添加了 volatile，可以解决可见性问题。
 * <p>
 * 2. 验证 volatile 不保证原子性
 * 2.1 原子性指的是什么意思？
 * 不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割，需要整体完整。
 * 要么同时成功，要么同时失败。
 * 2.2 volatile 不保证原子性的案例演示
 * 2.3 why
 * 2.4 如何解决原子性？
 * 2.4.1 加sync
 * 2.4.2 使用我们的juc下AtomicInteger
 */
@Slf4j
public class VolatileMain {
    public static void main(String[] args) {
//        testUnVisible();
//        testVisible();
        testAtomic();
    }

    private static void testUnVisible() {
        Example example = new Example();

        new Thread(() -> {
            log.info("执行开始");
            // 暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                log.error("error=", e);
            }
            example.setNumber(60);
            log.info("执行结束");
        }, "AAA").start();

        // 第2个线程就是我们的main线程
        while (example.getNumber() == 0) {
            // main 线程就一直在这里等待循环，直到number值不等于0
        }

        log.info("number={}", example.getNumber());
    }

    // volatile 具有可见性
    private static void testVisible() {
        ExampleVolatile example = new ExampleVolatile();

        new Thread(() -> {
            log.info("执行开始");
            // 暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                log.error("error=", e);
            }
            example.setNumber(60);
            log.info("执行结束");
        }, "AAA").start();

        // 第2个线程就是我们的main线程
        while (example.getNumber() == 0) {
            // main 线程就一直在这里等待循环，直到number值不等于0
        }

        log.info("number={}", example.getNumber());
    }

    // volatile 不保证原子性
    private static void testAtomic() {
        ExampleVolatile example = new ExampleVolatile();

        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    example.incrNumber();
                    example.incrAtomic();
                }
                countDownLatch.countDown();
            }, i + "").start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("error=", e);
        }

        // number=19975, atomicInteger=20000
        log.info("number={}, atomicInteger={}", example.getNumber(), example.getAtomicInteger());
    }

    @Getter
    @Setter
    @ToString
    public static class Example {
        private int number = 0;
    }

    @Getter
    @Setter
    @ToString
    public static class ExampleVolatile {
        private volatile int number = 0;
        private AtomicInteger atomicInteger = new AtomicInteger();

        // 请注意，此时number前面是加了volatile关键字修饰的
        public void incrNumber() {
            number++;
        }

        public void incrAtomic() {
            atomicInteger.getAndIncrement();
        }
    }

//    public static class Example {
//        // private int number = 0;
//        private volatile int number = 0;
//        private AtomicInteger atomicInteger = new AtomicInteger();
//        private int userNum = 0;
//        private boolean inited = false;
//
//        public int getNumber() {
//            return number;
//        }
//
//        public void setNumber(int number) {
//            this.number = number;
//        }
//
//        public void init() {
//            userNum = 50; // 语句1，设置当前用户数为 50
//            inited = true; // 语句2，初始化完成
//        }
//
//        public void register() {
//            if (inited) { // 语句3，判断是否初始化完成
//                userNum = userNum + 1; // 语句4，初始化完成，注册用户数+1
//                System.out.println("userNum=" + userNum);
//            }
//        }
//
//        // 线程1执行 init() 方法，线程2执行 register() 方法
//        // 由于语句1和语句2没有数据依赖性，因此可能会被重排序。
//        // 假如发生了重排序，在线程1执行过程中先执行语句2，而此时线程2会以为初始化工作已经完成，那么就会执行语句4，用户数变为1，就会导致程序出错。
//    }
}


