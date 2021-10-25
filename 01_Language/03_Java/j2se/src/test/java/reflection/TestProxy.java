package reflection;

import com.onevgo.j2se.reflection.IExample;
import com.onevgo.j2se.reflection.Example;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class TestProxy {
    @Test
    public void test1() {
        Example person = new Example();
        DumpProxy<Example> handler = new DumpProxy<>(person);
//        IShow proxyInstance = (IShow) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{IShow.class}, handler);
        IExample proxyInstance = (IExample) Proxy.newProxyInstance(Example.class.getClassLoader(), Example.class.getInterfaces(), handler);
        proxyInstance.profile();
    }
}
