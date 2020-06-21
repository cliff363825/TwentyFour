package juc;

public class TestProducerConsumerLock {
    public static void main(String[] args) {
        IClerk clerk = new MyClerkLock();

        new Thread(new MyProducer(clerk), "Producer").start();
        new Thread(new MyConsumer(clerk), "Consumer").start();
    }
}
