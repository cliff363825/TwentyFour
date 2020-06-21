package com.onevgo.ssm.controller;

import com.onevgo.ssm.bean.Department;
import com.onevgo.ssm.dto.Msg;
import com.onevgo.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts() {
        List<Department> depts = departmentService.getDepts();

        return Msg.success().add("depts", depts);
    }
}
