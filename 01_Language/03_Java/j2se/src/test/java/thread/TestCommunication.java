package thread;

public class TestCommunication {
    public static void main(String[] args) {
        PrintNum printNum = new PrintNum();

        new Thread(printNum, "A").start();
        new Thread(printNum, "B").start();
    }
}
