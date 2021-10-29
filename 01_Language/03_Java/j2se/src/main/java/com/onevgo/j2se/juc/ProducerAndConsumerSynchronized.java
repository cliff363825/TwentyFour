package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerAndConsumerSynchronized implements IProducerAndConsumer {
    private int productNum = 0;

    @Override
    public synchronized void produce() {
        // 1. 判断
        while (productNum != 0) {
            // 停止生产
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("error=", e);
            }
        }

        // 2. 干活
        productNum++;
        log.info("生产商品={}", productNum);

        // 3. 通知唤醒
        notifyAll();
    }

    @Override
    public synchronized void consume() {
        // 1. 判断
        while (productNum == 0) {
            // 停止消费
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("error=", e);
            }
        }

        // 2. 干活
        productNum--;
        log.info("消费商品={}", productNum);

        // 3. 通知唤醒
        notifyAll();
    }
}
