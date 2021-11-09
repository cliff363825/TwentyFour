package com.onevgo.j2se.juc;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class FutureMain {
    private static final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, ThreadUtil.newNamedThreadFactory("scheduler-", false));
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
            60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), ThreadUtil.newNamedThreadFactory("worker-", false));

    public static void main(String[] args) {
        testFailAfter();

        scheduledThreadPoolExecutor.shutdown();
        threadPoolExecutor.shutdown();
    }

    private static void testFailAfter() {
        // failAfter
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("执行开始1");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                // @see: https://dzone.com/articles/completablefuture-cant-be#
                // CompletableFuture Can't Be Interrupted
                log.error("error=", e);
            }
            log.info("执行结束1");
            return 1024;
        }, threadPoolExecutor);
        CompletableFuture<Integer> future1WithTimeout = future1.applyToEither(
                failAfter(Duration.ofSeconds(1)),
                Function.identity()
        ).exceptionally((ex) -> {
            log.error("执行异常1", ex);
            return 0;
        });

        // within
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            log.info("执行开始2");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                log.error("error=", e);
            }
            log.info("执行结束2");
            return 1024;
        }, threadPoolExecutor);
        CompletableFuture<Integer> future2WithTimeout = within(future2, Duration.ofSeconds(1)).exceptionally((ex) -> {
            log.error("执行异常2", ex);
            return 0;
        });

        // interrupt
        ExampleSupplier<Integer> supplier = ExampleSupplier.of(() -> {
            log.info("执行开始3");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.error("error=", e);
            }
            log.info("执行结束3");
            return 1024;
        });
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(supplier, threadPoolExecutor);
        CompletableFuture<Integer> future3WithTimeout = within(future3, Duration.ofSeconds(1)).exceptionally((ex) -> {
            if (ex.getCause() instanceof TimeoutException) {
                supplier.interrupt();
            }
            log.error("执行异常3", ex);
            return 0;
        });

        try {
            Integer res = future1WithTimeout.get();
            log.info("返回结果1={}", res);
        } catch (InterruptedException | ExecutionException e) {
            log.error("error=", e);
        }

        try {
            Integer res = future2WithTimeout.get();
            log.info("返回结果2={}", res);
        } catch (InterruptedException | ExecutionException e) {
            log.error("error=", e);
        }

        try {
            Integer res = future3WithTimeout.get();
            log.info("返回结果3={}", res);
        } catch (InterruptedException | ExecutionException e) {
            log.error("error=", e);
        }
    }

    /**
     * @param duration
     * @param <T>
     * @return
     * @see <a href="https://dzone.com/articles/asynchronous-timeouts">Asynchronous Timeouts with CompletableFuture</a>
     */
    public static <T> CompletableFuture<T> failAfter(Duration duration) {
        CompletableFuture<T> promise = new CompletableFuture<>();
        scheduledThreadPoolExecutor.schedule(() -> {
            TimeoutException timeoutException = new TimeoutException("Timeout after " + duration);
            promise.completeExceptionally(timeoutException);
        }, duration.toMillis(), TimeUnit.MILLISECONDS);
        return promise;
    }

    public static <T> CompletableFuture<T> within(CompletableFuture<T> completableFuture, Duration duration) {
        return completableFuture.applyToEither(failAfter(duration), Function.identity());
    }

    public static class ExampleSupplier<T> implements Supplier<T> {
        private final Supplier<T> delegate;
        private volatile Thread runner;

        private ExampleSupplier(Supplier<T> supplier) {
            this.delegate = supplier;
        }

        public static <T> ExampleSupplier<T> of(Supplier<T> supplier) {
            return new ExampleSupplier<>(supplier);
        }

        @Override
        public T get() {
            runner = Thread.currentThread();
            return delegate.get();
        }

        public void interrupt() {
            if (runner != null && !runner.equals(Thread.currentThread())) {
                runner.interrupt();
            }
        }
    }

}
