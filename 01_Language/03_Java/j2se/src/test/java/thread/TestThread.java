package thread;

public class TestThread {
    public static void main(String[] args) {
        // 1. 创建线程
        Thread myThread = new MyThread();
        myThread.setName("A");
        // 2. 启动线程；并调用当前线程的run()方法
        myThread.start();

        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
