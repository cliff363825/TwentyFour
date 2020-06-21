package hello;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;

public class TestSystem {
    @Test
    public void testProperties() {
        Properties properties = System.getProperties();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry);
        }
    }
}
