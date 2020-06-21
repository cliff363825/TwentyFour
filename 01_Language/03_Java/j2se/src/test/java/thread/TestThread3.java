package thread;

public class TestThread3 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Window window = new Window();
            window.setName("窗口" + i);
            window.start();
        }
    }
}
