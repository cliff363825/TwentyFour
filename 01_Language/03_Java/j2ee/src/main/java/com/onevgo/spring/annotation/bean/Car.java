package com.onevgo.spring.annotation.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {
    public Car() {
        super();
        System.out.println("Car.Car");
    }

    public void init() {
        System.out.println("Car.init");
    }

    public void destroy() {
        System.out.println("Car.destroy");
    }
}
