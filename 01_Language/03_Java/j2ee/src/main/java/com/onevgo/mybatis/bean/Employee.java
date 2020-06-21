package com.onevgo.mybatis.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private Department dept;
    private EmpStatus empStatus;
}
