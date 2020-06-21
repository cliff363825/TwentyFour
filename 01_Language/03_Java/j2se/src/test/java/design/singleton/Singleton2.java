package design.singleton;

public class Singleton2 {
    public final static Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2() {
        System.out.println(Thread.currentThread().getName() + ": " + getClass().getName());
    }
}
