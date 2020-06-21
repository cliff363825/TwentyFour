package juc;

public class TestProducerConsumerSync {
    public static void main(String[] args) {
        IClerk clerk = new MyClerkSync();

        new Thread(new MyProducer(clerk), "Producer").start();
        new Thread(new MyConsumer(clerk), "Consumer").start();
    }
}
