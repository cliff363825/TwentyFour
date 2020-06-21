package juc;

import org.junit.Test;

import java.util.concurrent.*;

public class TestThreadPool {
    @Test
    public void test1() {
        System.out.println(Runtime.getRuntime().availableProcessors()); // 4
    }

    public static void main(String[] args) {
        // testBuiltInThreadPool();
//        testCustomThreadPool();
        testScheduledThreadPool();
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
                System.out.println(Thread.currentThread().getName() + ": 办理业务");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPool.shutdown();
    }

    public static void testBuiltInThreadPool() {
        // Array        Arrays
        // Collection   Collections
        // Executor     Executors

        // ExecutorService threadPool = Executors.newFixedThreadPool(5);// 一池5个处理线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();// 一池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();// 一池N个处理线程

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ": 办理业务");
                }
            });
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threadPool.shutdown();
    }

    public static void testScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            ScheduledFuture<?> future = scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ": i = " + temp);
                }
            }, 1, TimeUnit.SECONDS);
            System.out.println("future = " + future);
        }

        scheduledThreadPool.shutdown();
    }
}
