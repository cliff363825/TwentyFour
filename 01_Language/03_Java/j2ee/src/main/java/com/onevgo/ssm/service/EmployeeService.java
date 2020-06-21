package com.onevgo.ssm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.onevgo.ssm.bean.Employee;
import com.onevgo.ssm.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getAll() {
        return employeeMapper.selectList(Wrappers.emptyWrapper());
    }

    public IPage<Employee> getPage(IPage<Employee> page, Wrapper<Employee> wrapper) {
        return employeeMapper.selectPage(page, wrapper);
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insert(employee);
    }

    public boolean checkUser(String empName) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_name", empName);
        Integer count = employeeMapper.selectCount(queryWrapper);

        return count == 0;
    }

    public Employee getEmp(Integer id) {
        return employeeMapper.selectById(id);
    }

    public void updateEmp(Employee employee) {
        employeeMapper.updateById(employee);
    }

    public void deleteEmp(Integer id) {
        employeeMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        employeeMapper.deleteBatchIds(ids);
    }
}
