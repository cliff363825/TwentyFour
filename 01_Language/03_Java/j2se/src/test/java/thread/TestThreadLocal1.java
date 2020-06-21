package thread;

public class TestThreadLocal1 implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        String name = null;
        for (; i < 10; i++) {
            //synchronized (this) {
            name = Thread.currentThread().getName();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ": " + name);
            //}
        }
    }

    public static void main(String[] args) {
        TestThreadLocal1 tlt = new TestThreadLocal1();

        new Thread(tlt, "AAA").start();
        new Thread(tlt, "BBB").start();
    }
}
