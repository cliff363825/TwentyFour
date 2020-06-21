package thread;

public class Window extends Thread {
    private static int ticket = 100;
    private static final Object lock = new Object();

    @Override
    public void run() {
        while (ticket > 0) {
            synchronized (lock) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket--);
                }
            }
        }
    }
}
