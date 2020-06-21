package thread;

public class TestThread4 {
    public static void main(String[] args) {
        Window1 window = new Window1();

        for (int i = 0; i < 10; i++) {
            new Thread(window, "窗口" + i).start();
        }
    }
}
