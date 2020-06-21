package design.proxy;

/**
 * 代理类
 */
public class MyProxy implements Subject {
    private Subject subject;

    public MyProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("MyProxy.request start...");
        subject.request();
        System.out.println("MyProxy.request end...");
    }
}
