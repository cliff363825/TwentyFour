package com.onevgo.jooq.service;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static com.onevgo.jooq.Tables.TBL_EMPLOYEE;

public class EmployeeService {
    @Autowired
    private DSLContext dsl;
    @Transactional
    public void addEmployee(String name, String email, String gender) {
        dsl.insertInto(TBL_EMPLOYEE,
                TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.EMAIL, TBL_EMPLOYEE.GENDER)
                .values(name, email, gender)
                .execute();

        int i = 10 / 0;
    }
}
