package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskMain {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask1 = new FutureTask<>(new ExampleCallable());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new ExampleCallable());

        new Thread(futureTask1, "t1").start();
        new Thread(futureTask2, "t2").start();

        try {
            Integer res1 = futureTask1.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            log.error("error=", e);
        } catch (TimeoutException e) {
            log.error("error=", e);
            log.info("isDone={}", futureTask1.isDone());
        }
        try {
            Integer res2 = futureTask2.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("error=", e);
        }
    }

    @Slf4j
    public static class ExampleCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log.info("执行开始。。。");
            TimeUnit.SECONDS.sleep(2);
            log.info("执行结束。。。");
            return 1024;
        }
    }
}
