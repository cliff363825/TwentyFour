package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ProducerAndConsumerBlockingQueue {
    public static void main(String[] args) {
        IProducerAndConsumer example = new Example(new ArrayBlockingQueue<>(10));

        Thread t1 = new Thread(() -> {
            log.info("生产线程启动");
            example.produce();
        }, "Producer");

        Thread t2 = new Thread(() -> {
            log.info("消费线程启动");
            example.consume();
        }, "Consumer");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            log.error("error=", e);
        }
    }

    public static class Example implements IProducerAndConsumer {
        private BlockingQueue<String> blockingQueue;
        private AtomicInteger atomicInteger = new AtomicInteger();

        public Example(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void produce() {
            while (true) {
                String data = atomicInteger.incrementAndGet() + "";
                try {
                    boolean b = blockingQueue.offer(data, 5, TimeUnit.SECONDS);
                    if (b) {
                        log.info("插入队列成功={}", data);
                    } else {
                        log.info("插入队列失败={}", data);
                    }
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("error=", e);
                    break;
                }
            }
        }

        @Override
        public void consume() {
            while (true) {
                try {
                    String data = blockingQueue.poll(5, TimeUnit.SECONDS);
                    if (data == null) {
                        break;
                    }
                    log.info("消费队列={}", data);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("error=", e);
                    break;
                }
            }
        }
    }
}
