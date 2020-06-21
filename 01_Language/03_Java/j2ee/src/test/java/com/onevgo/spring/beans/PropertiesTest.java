package com.onevgo.spring.beans;

import com.onevgo.spring.beans.properties.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertiesTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans-properties.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void test01() {
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource);
    }
}
