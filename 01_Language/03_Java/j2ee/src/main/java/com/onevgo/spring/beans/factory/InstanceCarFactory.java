package com.onevgo.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

public class InstanceCarFactory {
    private Map<String, Car> cars;

    public InstanceCarFactory() {
        cars = new HashMap<>();
        cars.put("audi", new Car("audi", 300000));
        cars.put("ford", new Car("ford", 400000));
    }

    public Car getCar(String brand) {
        return cars.get(brand);
    }
}
