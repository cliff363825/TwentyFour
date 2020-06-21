package com.onevgo;

import lombok.Data;

@Data
public class Customer {
    private Integer id;
    private String name;
    private int age;

    public Customer() {
    }

    public Customer(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
