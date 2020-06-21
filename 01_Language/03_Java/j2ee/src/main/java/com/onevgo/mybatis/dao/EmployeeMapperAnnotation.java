package com.onevgo.mybatis.dao;

import com.onevgo.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where id=#{id}")
    Employee getEmpById(Integer id);
}
