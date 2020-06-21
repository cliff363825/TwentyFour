package com.onevgo.spring.beans.annotation.repository;

import com.onevgo.spring.beans.annotation.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired(required = false)
    private TestObject testObject;

    @Override
    public void save() {
        System.out.println("UserRepositoryImpl.save");
        System.out.println(testObject);
    }
}
