package reflection;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class TestProxy {
    @Test
    public void test1() {
        Person person = new Person();
        DumpProxy<Person> handler = new DumpProxy<>(person);
//        IShow proxyInstance = (IShow) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{IShow.class}, handler);
        IShow proxyInstance = (IShow) Proxy.newProxyInstance(Person.class.getClassLoader(), Person.class.getInterfaces(), handler);
        proxyInstance.show();
    }
}
