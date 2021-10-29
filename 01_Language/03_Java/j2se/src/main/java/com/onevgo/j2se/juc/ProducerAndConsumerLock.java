package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ProducerAndConsumerLock implements IProducerAndConsumer {
    private int productNum = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Override
    public void produce() {
        lock.lock();
        try {
            // 1. 判断
            while (productNum != 0) {
                // 停止生产
                condition.await();
            }

            // 2. 干活
            productNum++;
            log.info("生产商品={}", productNum);

            // 3. 通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            log.error("error=", e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void consume() {
        lock.lock();
        try {
            // 1. 判断
            while (productNum == 0) {
                // 停止消费
                condition.await();
            }

            // 2. 干活
            productNum--;
            log.info("取走商品={}", productNum);

            // 3. 通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            log.error("error=", e);
        } finally {
            lock.unlock();
        }
    }
}
