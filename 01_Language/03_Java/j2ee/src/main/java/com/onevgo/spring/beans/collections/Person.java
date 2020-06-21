package com.onevgo.spring.beans.collections;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Person {
    private String name;
    private int age;
    private Car car;
    private List<Car> carList;
    private Map<String, Car> carMap;
    private Properties properties;

    public Person() {
    }

    public Person(String name, int age, Car car, List<Car> carList, Map<String, Car> carMap, Properties properties) {
        this.name = name;
        this.age = age;
        this.car = car;
        this.carList = carList;
        this.carMap = carMap;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public Map<String, Car> getCarMap() {
        return carMap;
    }

    public void setCarMap(Map<String, Car> carMap) {
        this.carMap = carMap;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                ", carList=" + carList +
                ", carMap=" + carMap +
                ", properties=" + properties +
                '}';
    }
}
