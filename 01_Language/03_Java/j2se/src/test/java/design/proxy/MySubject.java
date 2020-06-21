package design.proxy;

/**
 * 被代理类
 */
public class MySubject implements Subject {
    @Override
    public void request() {
        System.out.println("MySubject.request");
    }
}
