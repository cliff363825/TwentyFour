package com.onevgo.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void updatePersonEmail(String email, Integer id) {
        personRepository.updatePersonEmail(id, email);
    }

    @Transactional
    public void savePersons(List<Person> personList) {
        personRepository.save(personList);
    }
}
