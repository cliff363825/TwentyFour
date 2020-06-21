package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class TestSpinLock {
    // 原子引用线程
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + ": 尝试获取锁");

        while (!atomicReference.compareAndSet(null, thread)) {
        }

        System.out.println(Thread.currentThread().getName() + ": 获取锁成功");
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + ": 释放锁");

        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        TestSpinLock testSpinLock = new TestSpinLock();

        new Thread(() -> {
            testSpinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testSpinLock.myUnlock();
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            testSpinLock.myLock();
            testSpinLock.myUnlock();
        }, "t2").start();

        //t1: 尝试获取锁
        //t1: 获取锁成功
        //t2: 尝试获取锁
        //t1: 释放锁
        //t2: 获取锁成功
        //t2: 释放锁
    }
}
