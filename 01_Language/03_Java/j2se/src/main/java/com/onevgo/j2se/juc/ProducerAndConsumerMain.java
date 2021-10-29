package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerAndConsumerMain {
    public static void main(String[] args) {
        // synchronized
//        IProducerAndConsumer producerAndConsumer = new ProducerAndConsumerSynchronized();
        // lock
        IProducerAndConsumer producerAndConsumer = new ProducerAndConsumerLock();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    log.error("error=", e);
                }
                producerAndConsumer.produce();
            }
        }, "Producer").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    log.error("error=", e);
                }
                producerAndConsumer.consume();
            }
        }, "Consumer").start();
    }

}
