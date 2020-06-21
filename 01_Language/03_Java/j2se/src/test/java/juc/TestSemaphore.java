package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);// 模拟3个停车位

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + ": 抢到车位");
                    TimeUnit.SECONDS.sleep(3); // 暂停一会儿线程
                    System.out.println(Thread.currentThread().getName() + ": 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, i + "").start();
        }
    }
}
