package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DeadLockMain {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "AAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "BBB").start();
        // jps -l: 定位进程号
        // 54385 juc.TestDeadLock

        // jstack 54385: 找到死锁查看
    }

    public static class HoldLockThread implements Runnable {
        private final String lockA;
        private final String lockB;

        public HoldLockThread(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA) {
                log.info("获取锁 {}", lockA);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("error=", e);
                }
                log.info("尝试获取锁 {}", lockB);
                synchronized (lockB) {
                    log.info("获取锁 {}", lockB);
                }
            }
        }
    }
}
