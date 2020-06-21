package lock;

public class TestReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 30; i++) {
            final int tempInt = i;
            new Thread(() -> myCache.put(tempInt + "", tempInt + ""), i + "").start();
        }

        for (int i = 0; i < 30; i++) {
            final int tempInt = i;
            new Thread(() -> myCache.get(tempInt + ""), i + "").start();
        }
    }
}
