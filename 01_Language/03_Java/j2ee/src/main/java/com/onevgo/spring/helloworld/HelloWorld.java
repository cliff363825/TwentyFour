package com.onevgo.spring.helloworld;

public class HelloWorld {
    private String name;

    public HelloWorld() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("Hello, " + name);
    }
}
