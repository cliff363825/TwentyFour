package com.onevgo.mybatis.dao;

import com.onevgo.mybatis.bean.Department;

public interface DepartmentMapper {
    Department getDeptById(Integer id);

    Department getDeptWithEmpsById(Integer id);

    Department getDeptWithEmpsStepById(Integer id);
}
