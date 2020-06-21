package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestABA {
    private static AtomicReference<String> atomicReference = new AtomicReference<>("A");
    private static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("A", 1);

    public static void main(String[] args) {
        System.out.println("以下是ABA问题的产生");

        new Thread(() -> {
            atomicReference.compareAndSet("A", "B"); // A -> B
            atomicReference.compareAndSet("B", "A"); // B -> A
        }, "t1").start();
        new Thread(() -> {
            try {
                // t2线程暂停1秒钟，保证上面的t1线程完成了一次ABA操作
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicReference.compareAndSet("A", "C"); // A -> C
            System.out.println(b + ": " + atomicReference.get());
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("以下是ABA问题的解决");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + ": 第一次版本号 " + stamp); // 1
            // t3线程暂停1秒钟
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet("A", "B", stamp, stamp + 1); // A -> B, 1 -> 2
            System.out.println(Thread.currentThread().getName() + ": 第二次版本号 " + atomicStampedReference.getStamp()); // 2

            atomicStampedReference.compareAndSet("B", "A", atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1); // B -> A, 2 -> 3
            System.out.println(Thread.currentThread().getName() + ": 第三次版本号 " + atomicStampedReference.getStamp()); // 3
        }, "t3").start();
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + ": 第一次版本号 " + stamp); // 1
            // t4线程暂停3秒钟，保证上面t3线程完成了一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean b = atomicStampedReference.compareAndSet("A", "C", stamp, stamp + 1); // A -> C, 1 -> 2 => false
            System.out.println(b + ": " + atomicStampedReference.getReference() + ", " + atomicStampedReference.getStamp());
        }, "t4").start();
    }
}
