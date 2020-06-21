package com.onevgo.ssm;

import com.onevgo.ssm.bean.Employee;
import com.onevgo.ssm.dao.DepartmentMapper;
import com.onevgo.ssm.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

// 推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
// 1. 导入 SpringTest 模块
// 2. @ContextConfiguration 指定 Spring 配置文件的位置
// 3. 直接 autowired 要使用的组件即可
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ssm.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD() {
        // 1. 插入几个部门
        /*
        Department department = new Department();
        department.setDeptName("开发部");

        Department department1 = new Department();
        department1.setDeptName("测试部");

        departmentMapper.insertSelective(department);
        departmentMapper.insertSelective(department1);
        */

        // 2. 插入员工
        /*
        Employee employee1 = new Employee();
        employee1.setEmpName("Jerry");
        employee1.setGender("M");
        employee1.setEmail("Jerry@onevgo.com");
        employee1.setdId(1);
        employeeMapper.insertSelective(employee1);
        */

        // 3. 批量插入多个员工 批量，使用可以执行批量操作的 sqlSession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            Employee employee1 = new Employee();
            String s = UUID.randomUUID().toString().substring(0, 5) + i;
            employee1.setEmpName(s);
            employee1.setGender("M");
            employee1.setEmail(s + "@onevgo.com");
            employee1.setDId(1);
            mapper.insert(employee1);
        }
    }
}
