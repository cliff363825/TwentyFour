package thread;

public class TestThread2 {
    public static void main(String[] args) {
        Ticket t = new Ticket();

        for (int i = 0; i < 10; i++) {
            new Thread(t, "t" + i + "窗口").start();
        }
    }
}
