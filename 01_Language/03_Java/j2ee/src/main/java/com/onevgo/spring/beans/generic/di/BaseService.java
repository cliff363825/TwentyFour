package com.onevgo.spring.beans.generic.di;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

    @Autowired
    private BaseRepository<T> repository;

    public void add() {
        System.out.println("BaseService.add");
        System.out.println(repository);
    }
}
