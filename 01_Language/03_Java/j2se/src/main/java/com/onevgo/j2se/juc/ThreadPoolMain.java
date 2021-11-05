package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolMain {
    public static void main(String[] args) {
//        testBuiltInThreadPool();
//        testScheduledThreadPool();
        testCustomThreadPool();
    }

    private static void testBuiltInThreadPool() {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);// 一池5个处理线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 一池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();// 一池N个处理线程

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                log.info("办理业务开始");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("error=", e);
                }
                log.info("办理业务结束");
            });
        }

        log.info("shutdown={}, terminated={}", threadPool.isShutdown(), threadPool.isTerminated());
        threadPool.shutdown();
        log.info("shutdown={}, terminated={}", threadPool.isShutdown(), threadPool.isTerminated());
        try {
            boolean b = threadPool.awaitTermination(100, TimeUnit.MILLISECONDS);
            if (b) {
                log.info("业务办理完成");
            } else {
                log.info("业务办理未完成");
            }
        } catch (InterruptedException e) {
            log.error("error=", e);
        }
        log.info("shutdown={}, terminated={}", threadPool.isShutdown(), threadPool.isTerminated());
    }

    private static void testScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            scheduledThreadPool.schedule(() -> log.info("startTime={}, now={}", startTime, System.currentTimeMillis()), 1, TimeUnit.SECONDS);
        }

        scheduledThreadPool.shutdown();
        try {
            boolean b = scheduledThreadPool.awaitTermination(2, TimeUnit.SECONDS);
            log.info("done={}", b);
        } catch (InterruptedException e) {
            log.error("error=", e);
        }
    }

    public static void testCustomThreadPool() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy()
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                //new ThreadPoolExecutor.DiscardPolicy()
        );

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                log.info("办理业务开始");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("error=", e);
                }
                log.info("办理业务结束");
            });
        }

        threadPool.shutdown();
    }

}
