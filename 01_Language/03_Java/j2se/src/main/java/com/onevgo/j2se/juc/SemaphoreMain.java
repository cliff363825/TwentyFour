package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreMain {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);// 模拟3个停车位

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();

                    log.info("抢到车位");
                    TimeUnit.SECONDS.sleep(3); // 暂停一会儿线程
                    log.info("离开车位");
                } catch (InterruptedException e) {
                    log.error("error=", e);
                } finally {
                    semaphore.release();
                }
            }, "t" + i).start();
        }
    }
}
