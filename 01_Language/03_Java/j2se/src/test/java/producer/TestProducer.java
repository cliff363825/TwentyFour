package producer;

public class TestProducer {

    public static void main(String[] args) {
        Resource resource = new Resource();

        Producer p1 = new Producer(resource);
        Producer p2 = new Producer(resource);
        Consumer c1 = new Consumer(resource);
        Consumer c2 = new Consumer(resource);
        Consumer c3 = new Consumer(resource);
        Consumer c4 = new Consumer(resource);

        Thread t1 = new Thread(p1);
        t1.setName("生产者1");
        Thread t2 = new Thread(p2);
        t2.setName("生产者2");
        Thread t3 = new Thread(c1);
        t3.setName("消费者1");
        Thread t4 = new Thread(c2);
        t4.setName("消费者2");
        Thread t5 = new Thread(c3);
        t5.setName("消费者3");
        Thread t6 = new Thread(c4);
        t6.setName("消费者4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
