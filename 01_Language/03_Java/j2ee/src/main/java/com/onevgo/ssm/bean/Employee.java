package com.onevgo.ssm.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@TableName("employee")
public class Employee {
    @TableId
    private Integer empId;

    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})",
            message = "用户名必须是6-16位数字和字母的组合或者2-5位中文")
    private String empName;

    private String gender;

    @Pattern(regexp = "([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",
            message = "邮箱格式不正确")
    private String email;

    private Integer dId;

    @TableField(exist = false)
    // 希望查询员工的同时部门信息也是查询好的
    private Department department;
}