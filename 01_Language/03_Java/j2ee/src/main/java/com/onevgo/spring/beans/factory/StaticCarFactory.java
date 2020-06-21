package com.onevgo.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

public class StaticCarFactory {
    private static Map<String, Car> cars = new HashMap<>();

    static {
        cars.put("audi", new Car("audi", 3000000d));
        cars.put("ford", new Car("ford", 4000000d));
    }

    public static Car getCar(String name) {
        return cars.get(name);
    }
}
