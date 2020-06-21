package com.onevgo.jpa;

import com.onevgo.jpa.spring.entities.Person;
import com.onevgo.jpa.spring.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class SpringTest {
    private ApplicationContext applicationContext;
    private PersonService personService;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring-jpa.xml");
        personService = applicationContext.getBean(PersonService.class);
    }

    @Test
    public void testPersonService() {
        Person person1 = new Person();
        person1.setAge(11);
        person1.setEmail("aa@163.com");
        person1.setLastName("AA");

        Person person2 = new Person();
        person2.setAge(12);
        person2.setEmail("bb@163.com");
        person2.setLastName("BB");

        System.out.println(personService.getClass().getName());
        personService.savePersons(person1, person2);
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource.getConnection());
    }
}
