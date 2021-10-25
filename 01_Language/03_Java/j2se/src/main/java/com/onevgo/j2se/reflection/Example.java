package com.onevgo.j2se.reflection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@Slf4j
public class Example implements IExample {
    private String name;
    private int age;

    public Example() {
    }

    public Example(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void info() {
        log.info("执行info方法...");
    }

    @Override
    public void profile() {
        log.info("name={} age={}", name, age);
    }

}
