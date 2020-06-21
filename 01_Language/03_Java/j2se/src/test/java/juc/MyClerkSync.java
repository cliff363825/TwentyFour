package juc;

public class MyClerkSync implements IClerk {
    private int productNum = 0;

    public synchronized void produce() {
        // 1. 判断
        while (productNum != 0) {
            // 停止生产
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2. 干活
        productNum++;
        System.out.println(Thread.currentThread().getName() + ": 生产商品=" + productNum);

        // 3. 通知唤醒
        notifyAll();
    }

    public synchronized void consume() {
        // 1. 判断
        while (productNum == 0) {
            // 停止消费
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2. 干活
        productNum--;
        System.out.println(Thread.currentThread().getName() + ": 取走商品=" + productNum);

        // 3. 通知唤醒
        notifyAll();
    }
}
