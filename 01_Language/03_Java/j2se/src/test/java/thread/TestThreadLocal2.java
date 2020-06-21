package thread;

public class TestThreadLocal2 implements Runnable {
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private int i = 0;

    @Override
    public void run() {
        String name = null;
        for (; i < 10; i++) {
            name = Thread.currentThread().getName();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            threadLocal.set(name);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        TestThreadLocal2 tlt = new TestThreadLocal2();

        new Thread(tlt, "AAA").start();
        new Thread(tlt, "BBB").start();
    }
}
