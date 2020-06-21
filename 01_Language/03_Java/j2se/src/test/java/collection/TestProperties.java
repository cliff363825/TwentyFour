package collection;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    @Test
    public void testProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("jdbc.properties"));
        System.out.println("properties.getProperty(\"user\") = " + properties.getProperty("user"));
        System.out.println("properties.getProperty(\"password\") = " + properties.getProperty("password"));
    }
}
