package com.onevgo.spring.service.impl;

import com.onevgo.spring.service.PersonService;

public class PersonServiceImpl implements PersonService {
    @Override
    public void save() {
        System.out.println("PersonService's save...");
    }
}
