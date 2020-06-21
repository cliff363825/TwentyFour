package com.onevgo.spring.beans.cycle;

public class Car {
    private String brand;

    public Car() {
        System.out.println("Car.Car");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        System.out.println("Car.setBrand: " + brand);
        this.brand = brand;
    }

    public void init() {
        System.out.println("Car.init");
    }

    public void destroy() {
        System.out.println("Car.destroy");
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
