package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class CyclicBarrierMain {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> log.info("召唤神龙"));

        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                log.info("收集到第" + tempInt + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.error("error=", e);
                }
            }, "t" + i).start();
        }
    }
}
