package com.onevgo.j2se.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Department.class)
public @interface Employee {
    String value();

    String name();

    int age();
}
