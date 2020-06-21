package design.singleton;

public class Singleton1 {
    public final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
        System.out.println(Thread.currentThread().getName() + ": " + getClass().getName());
    }
}
