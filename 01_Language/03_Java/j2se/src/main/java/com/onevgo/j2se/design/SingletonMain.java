package com.onevgo.j2se.design;

import com.onevgo.j2se.design.singleton.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class SingletonMain {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.error("error=", e);
                }
                EagerSingleton eagerSingleton = EagerSingleton.getInstance();
                EagerSingleton1 eagerSingleton1 = EagerSingleton1.INSTANCE;
                EagerSingleton2 eagerSingleton2 = EagerSingleton2.INSTANCE;
                EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
                InnerSingleton innerSingleton = InnerSingleton.getInstance();
                LazyThreadUnsafeSingleton lazyThreadUnsafeSingleton = LazyThreadUnsafeSingleton.getInstance(); // 这种方式创建的单例，线程不安全
                LazyThreadUnsafeSingleton2 lazyThreadUnsafeSingleton2 = LazyThreadUnsafeSingleton2.getInstance(); // 这种方式创建的单例，线程不安全
                LazyThreadSafeSingleton lazyThreadSafeSingleton = LazyThreadSafeSingleton.getInstance();
            }, i + "").start();
        }
    }
}
