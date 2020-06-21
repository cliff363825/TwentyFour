package design;

import design.proxy.MySubject;
import design.proxy.MyProxy;
import design.proxy.Subject;
import org.junit.Test;

public class TestProxy {
    @Test
    public void testMyProxy() {
        Subject subject = new MySubject();
        Subject proxy = new MyProxy(subject);
        proxy.request();
    }
}
