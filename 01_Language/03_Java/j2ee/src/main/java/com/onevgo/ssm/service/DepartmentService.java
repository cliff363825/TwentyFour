package com.onevgo.ssm.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.onevgo.ssm.bean.Department;
import com.onevgo.ssm.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts() {
        return departmentMapper.selectList(Wrappers.emptyWrapper());
    }
}
