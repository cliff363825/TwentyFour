package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class SynchronizedAndLockMain {
    public static void main(String[] args) {
        Example example = new Example();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.print1();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.print2();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.print3();
            }
        }, "C").start();
    }

    public static class Example {
        private int number = 1;
        private Lock lock = new ReentrantLock();
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();

        public void print1() {
            lock.lock();
            try {
                // 1. 判断
                while (number != 1) {
                    c1.await();
                }

                // 2. 干活
                for (int i = 0; i < 5; i++) {
                    log.info("print1={}", i);
                }
                number = 2;

                // 3. 通知唤醒
                c2.signal();
            } catch (InterruptedException e) {
                log.error("error=", e);
            } finally {
                lock.unlock();
            }
        }

        public void print2() {
            lock.lock();
            try {
                // 1. 判断
                while (number != 2) {
                    c2.await();
                }

                // 2. 干活
                for (int i = 0; i < 10; i++) {
                    log.info("print2={}", i);
                }
                number = 3;

                // 3. 通知唤醒
                c3.signal();
            } catch (InterruptedException e) {
                log.error("error=", e);
            } finally {
                lock.unlock();
            }
        }

        public void print3() {
            lock.lock();
            try {
                // 1. 判断
                while (number != 3) {
                    c3.await();
                }

                // 2. 干活
                for (int i = 0; i < 15; i++) {
                    log.info("print3={}", i);
                }
                number = 1;

                // 3. 通知唤醒
                c1.signal();
            } catch (InterruptedException e) {
                log.error("error=", e);
            } finally {
                lock.unlock();
            }
        }
    }
}
