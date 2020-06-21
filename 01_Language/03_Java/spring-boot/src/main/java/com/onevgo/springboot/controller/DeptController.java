package com.onevgo.springboot.controller;

import com.onevgo.springboot.bean.Department;
import com.onevgo.springboot.bean.Employee;
import com.onevgo.springboot.mapper.DepartmentMapper;
import com.onevgo.springboot.mapper.EmployeeMapper;
import com.onevgo.springboot.service.DepartmentService;
import com.onevgo.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeptController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentService.getDeptById(id);
    }

    @GetMapping("/dept")
    public Department insertDept(Department department) {
        departmentService.insertDept(department);
        return department;
    }

    @GetMapping("/emp2/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeService.getEmpById(id);
    }
}
