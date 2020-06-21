package com.onevgo.sssp.entity;

import javax.persistence.*;

@Cacheable
@Entity
@Table(name = "SSSP_DEPARTMENTS")
public class Department {
    private Integer id;
    private String departmentName;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
