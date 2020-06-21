package com.onevgo.jpa.spring.service;

import com.onevgo.jpa.spring.dao.PersonDAO;
import com.onevgo.jpa.spring.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    @Transactional
    public void savePersons(Person person1, Person person2) {
        personDAO.save(person1);
        int i = 10 / 0;
        personDAO.save(person2);
    }
}
