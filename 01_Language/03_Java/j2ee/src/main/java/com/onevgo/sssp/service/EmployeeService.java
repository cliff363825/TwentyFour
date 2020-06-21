package com.onevgo.sssp.service;

import cn.hutool.core.bean.BeanUtil;
import com.onevgo.sssp.entity.Employee;
import com.onevgo.sssp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void save(Employee employee) {
        if (employee.getId() != null) {
            Employee employeeEntity = get(employee.getId());
            if (employeeEntity != null) {
                Map<String, Object> map = BeanUtil.beanToMap(employee, false, true);
                BeanUtil.fillBeanWithMap(map, employee, true);
                employeeRepository.save(employeeEntity);
            }
            return;
        }
        employee.setCreateTime(new Date());
        employeeRepository.saveAndFlush(employee);
    }

    @Transactional(readOnly = true)
    public Employee get(Integer id) {
        Employee employee = employeeRepository.findOne(id);
        return employee;
    }

    @Transactional(readOnly = true)
    public boolean existsByLastName(String lastName) {
        return employeeRepository.existsByLastName(lastName);
    }

    @Transactional(readOnly = true)
    public List<Employee> getByLastName(String lastName) {
        return employeeRepository.getByLastName(lastName);
    }

    @Transactional(readOnly = true)
    public Page<Employee> getPage(int pageNo, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
        Page<Employee> page = employeeRepository.findAll(pageRequest);
        return page;
    }

    @Transactional
    public void delete(Integer id) {
        employeeRepository.delete(id);
    }
}
