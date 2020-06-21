package com.onevgo.spring.struts2.action;

import com.onevgo.spring.service.PersonService;

public class PersonAction {
    private PersonService personService;
    public String execute() {
        personService.save();
        return "success";
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
