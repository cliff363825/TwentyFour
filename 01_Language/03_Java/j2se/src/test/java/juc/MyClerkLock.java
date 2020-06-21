package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyClerkLock implements IClerk {
    private int productNum = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() {
        lock.lock();
        try {
            // 1. 判断
            while (productNum != 0) {
                // 停止生产
                condition.await();
            }

            // 2. 干活
            productNum++;
            System.out.println(Thread.currentThread().getName() + ": 生产商品=" + productNum);

            // 3. 通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            // 1. 判断
            while (productNum == 0) {
                // 停止消费
                condition.await();
            }

            // 2. 干活
            productNum--;
            System.out.println(Thread.currentThread().getName() + ": 取走商品=" + productNum);

            // 3. 通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
