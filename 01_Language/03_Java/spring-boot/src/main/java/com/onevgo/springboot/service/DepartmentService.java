package com.onevgo.springboot.service;

import com.onevgo.springboot.bean.Department;
import com.onevgo.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public Department getDeptById(Integer id) {
        return departmentMapper.getDeptById(id);
    }

    public void insertDept(Department department) {
        departmentMapper.insertDept(department);
    }
}
