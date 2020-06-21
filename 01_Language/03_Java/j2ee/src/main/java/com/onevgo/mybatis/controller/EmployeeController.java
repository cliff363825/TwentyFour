package com.onevgo.mybatis.controller;

import com.onevgo.mybatis.bean.Employee;
import com.onevgo.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/getemps")
    public String emps(Model model) {
        List<Employee> emps = employeeService.getEmps();
        model.addAttribute("emps", emps);

        return "emps";
    }
}
