package com.onevgo.springboot;

import com.onevgo.springboot.bean.Department;
import com.onevgo.springboot.controller.DeptController;
import com.onevgo.springboot.service.DepartmentService;
import com.onevgo.springboot.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DeptController.class)
public class WebMvcTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DepartmentService departmentService;
    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testExample() throws Exception {
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("开发");

        given(this.departmentService.getDeptById(1))
                .willReturn(department);
        this.mvc.perform(get("/dept/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("{\"id\":1,\"departmentName\":\"开发\"}"));
    }
}
