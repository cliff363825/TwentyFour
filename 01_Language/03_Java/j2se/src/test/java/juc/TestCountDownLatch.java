package juc;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国被灭");
                countDownLatch.countDown();
            }, CountryEnum.getCountryEnumById(i).getName()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + ": 统一六国");
    }
}
