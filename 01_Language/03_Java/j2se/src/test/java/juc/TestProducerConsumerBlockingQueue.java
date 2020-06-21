package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestProducerConsumerBlockingQueue {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": 生产线程启动");
            try {
                myResource.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producer").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": 消费线程启动");
            try {
                myResource.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myResource.stop();
    }
}
