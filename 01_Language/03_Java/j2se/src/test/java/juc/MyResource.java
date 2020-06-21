package juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyResource {
    private volatile boolean FLAG = true; // 默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void produce() throws InterruptedException {
        String data = null;
        boolean retValue = false;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + ": 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + ": 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + ": 生产结束");
    }

    public void consume() throws InterruptedException {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                FLAG = false;
            } else {
                System.out.println(Thread.currentThread().getName() + ": 消费队列" + result + "成功");
                TimeUnit.SECONDS.sleep(1);
            }
        }

        System.out.println(Thread.currentThread().getName() + ": 消费结束");
    }

    public void stop() {
        FLAG = false;
    }
}
