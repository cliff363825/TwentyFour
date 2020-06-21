package reflection;

import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestParameterNameDiscoverer {
    @Test
    public void testLocalVariableTableParameterNameDiscoverer() {
        ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

        Method[] declaredMethods = Person.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            System.out.println(method + ": " + Arrays.toString(parameterNames));
        }
    }
}
