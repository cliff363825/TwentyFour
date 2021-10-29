package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CountDownLatchMain {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                log.info("{}国被灭", Thread.currentThread().getName());
                countDownLatch.countDown();
            }, CountryEnum.getCountryEnumById(i).getName()).start();
        }

        try {
            countDownLatch.await();
            log.info("统一六国");
        } catch (InterruptedException e) {
            log.error("error=", e);
        }
    }

    public enum CountryEnum {
        ONE(1, "齐"),
        TWO(2, "楚"),
        THREE(3, "燕"),
        FOUR(4, "赵"),
        FIVE(5, "魏"),
        SIX(6, "韩");

        private final int id;
        private final String name;

        CountryEnum(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public static CountryEnum getCountryEnumById(int id) {
            for (CountryEnum countryEnum : CountryEnum.values()) {
                if (countryEnum.getId() == id) {
                    return countryEnum;
                }
            }
            return null;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
