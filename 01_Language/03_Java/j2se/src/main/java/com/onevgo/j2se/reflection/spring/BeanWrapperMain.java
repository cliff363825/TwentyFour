package com.onevgo.j2se.reflection.spring;

import com.onevgo.j2se.reflection.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

@Slf4j
public class BeanWrapperMain {
    public static void main(String[] args) {
        Example target = new Example();

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(target);
        beanWrapper.setPropertyValue("name", "张三");
//        beanWrapper.setPropertyValue("name1", "李四"); // org.springframework.beans.NotWritablePropertyException
        beanWrapper.setPropertyValue("age", 20);
        beanWrapper.setPropertyValue("age", "22");

        log.info("{}", target); // Example(name=张三, age=22)
    }
}
