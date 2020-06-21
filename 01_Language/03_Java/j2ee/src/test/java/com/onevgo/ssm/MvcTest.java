package com.onevgo.ssm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.onevgo.ssm.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

// 需要 servlet-api 3.0的支持
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-ssm.xml", "file:src/main/webapp/WEB-INF/springSSM-servlet.xml"})
public class MvcTest {
    // 传入 Springmvc 的 ioc
    @Autowired
    WebApplicationContext context;
    // 虚拟mvc请求，获取到处理结果
    MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        // 模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "5"))
                .andReturn();

        // 请求成功以后，请求域中会有 pageInfo，我们可以取出 pageInfo 进行验证
        MockHttpServletRequest request = result.getRequest();
        IPage<Employee> pageInfo = (IPage<Employee>) request.getAttribute("pageInfo");
        System.out.println("当前页码：" + pageInfo.getCurrent());
        System.out.println("总页码：" + pageInfo.getPages());
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.println("在页面需要连续显示的页码：");

        // 获取员工数据
        List<Employee> list = pageInfo.getRecords();
        for (Employee e : list) {
            System.out.println("ID: " + e.getEmpId() + ", EmpName: " + e.getEmpName());
        }
    }
}
