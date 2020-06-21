package com.onevgo.mybatis.service;

import com.onevgo.mybatis.bean.Employee;
import com.onevgo.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SqlSession sqlSession;

    public List<Employee> getEmps() {
        return employeeMapper.getEmps();
    }
}
