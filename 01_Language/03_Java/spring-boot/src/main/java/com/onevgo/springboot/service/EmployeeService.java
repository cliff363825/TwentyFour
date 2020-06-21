package com.onevgo.springboot.service;

import com.onevgo.springboot.bean.Employee;
import com.onevgo.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee getEmpById(Integer id) {
        return employeeMapper.getEmpById(id);
    }
}
