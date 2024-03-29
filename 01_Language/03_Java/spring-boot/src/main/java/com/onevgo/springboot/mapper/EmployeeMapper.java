package com.onevgo.springboot.mapper;

import com.onevgo.springboot.bean.Employee;

// @Mapper或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
