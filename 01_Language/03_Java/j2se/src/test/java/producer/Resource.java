package producer;

class Resource {
    /**
     * 标志位:
     * flag == false: 资源为空，消费者停止，生产者生产，生产完后唤醒等待池中的线程
     * flag == true 资源不为空 生产者停止，消费者消费，消费完后唤醒等待池中的线程
     */
    private boolean flag = false;

    public synchronized void set() {
        //if判断标记，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况。
        //while判断标记，解决了线程获取执行权后，是否要运行！
        while (flag == true) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 生产 ...");
        flag = true;
        //notify:只能唤醒一个线程，如果本方唤醒了本方，没有意义。而且while判断标记+notify会导致死锁。
        //notifyAll解决了本方线程一定会唤醒对方线程的问题。
        this.notifyAll();
    }

    public synchronized void get() {
        //if判断标记，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况。
        //while判断标记，解决了线程获取执行权后，是否要运行！
        while (flag == false) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 消费 ...");
        flag = false;
        //notify:只能唤醒一个线程，如果本方唤醒了本方，没有意义。而且while判断标记+notify会导致死锁。
        //notifyAll解决了本方线程一定会唤醒对方线程的问题。
        this.notifyAll();
    }
}
