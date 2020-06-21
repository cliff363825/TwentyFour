package thread;

public class Ticket implements Runnable {
    private int ticket = 100;

    @Override
    public void run() {
        while (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket--);
        }
    }
}
