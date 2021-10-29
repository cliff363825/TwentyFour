package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ThreadUnsafeMain {
    // 写时复制
    // CopyOnWrite 容器即写时复制的容器，往容器添加元素的时候，不直接往当前容器 Object[] 添加，而是先将当前容器 Object[] 进行Copy，
    // 复制出一个新的容器 Object[] newElements，然后向新容器 Object[] newElements 里面添加元素，添加元素后，再将原容器的引用指向新的容器 setArray(newElements);
    // 这样做的好处是可以对 CopyOnWrite 容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素，
    // 所以 CopyOnWrite 容器也是一种读写分离的思想，读和写不同的容器。
    public static void main(String[] args) {
//        testList();
//        testSet();
        testMap();
    }

    private static void testList() {
//        List<String> list = new ArrayList<>(); // 线程不安全
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>(); // 写时复制

        CountDownLatch countDownLatch = new CountDownLatch(30);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                countDownLatch.countDown();
            }, "t" + i).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("error=", e);
        }

        // ArrayList: 29    线程不安全
        // Collections.synchronizedList: 30  线程安全
        // CopyOnWriteArrayList: 30     线程安全
        log.info("size={}", list.size());
    }

    private static void testSet() {
//        Set<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>(); // 写时复制

        CountDownLatch countDownLatch = new CountDownLatch(30);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                countDownLatch.countDown();
            }, "t" + i).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // HashSet: 29      线程不安全
        // Collections.synchronizedSet: 30  线程安全
        // CopyOnWriteArraySet: 30      线程安全
        log.info("size={}", set.size());
    }

    private static void testMap() {
//        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>(); // 常用

        CountDownLatch countDownLatch = new CountDownLatch(30);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                countDownLatch.countDown();
            }, "t" + i).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("error=", e);
        }

        // HashMap: 29  线程不安全
        // synchronizedMap: 30  线程安全
        // ConcurrentHashMap: 30    线程安全
        log.info("size={}", map.size());
    }
}
