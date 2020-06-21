package com.onevgo.ssm.controller;

//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.onevgo.ssm.bean.Employee;
import com.onevgo.ssm.dto.Msg;
import com.onevgo.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class EmployeeController {
    private final static Logger logger = Logger.getLogger(EmployeeController.class.getName());
    @Autowired
    private EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public Msg deleteEmpById(@PathVariable("ids") String ids) {
        if (ids.contains("-")) {
            String[] idArr = ids.split("-");
            List<Integer> idList = new ArrayList<>();
            for (String id : idArr) {
                idList.add(Integer.parseInt(id));
            }
            employeeService.deleteBatch(idList);
        } else {
            int id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }

    /*
    @ResponseBody
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public Msg deleteEmpById(@PathVariable("id") Integer id) {
        employeeService.deleteEmp(id);
        return Msg.success();
    }
    */

    // 如果直接发送 ajax = PUT 形式的请求
    // 封装的数据 Employee [empId=1014,empName=null,gender=null,email=null,dId=null]
    // 问题
    // 请求体中有数据
    // 但是 Employee 对象封装不上
    // update tbl_emp where emp_id = 1014
    // 原因：
    // Tomcat：
    //      1. 将请求体中的数据，封装一个map
    //      2. request.getParameter("empName") 就会从这个 map 中取值
    //      3. SpringMVC 封装 POJO 对象的时候
    //          会把 POJO 中每个属性的值，request.getParameter("email")
    // AJAX 发送 PUT 请求引发的血案
    //      PUT请求，请求体中的数据，request.getParameter("empName") 拿不到
    //      Tomcat一看是 PUT 不会封装请求体中的数据为map，只有 POST 形式的请求才封装请求体为 map
    // org.apache.catalina.connector.Request -- parseParameters
    // if (!getConnector().isParseBodyMethod(getMethod())) {...}
    // 解决方案
    // 我们要能支持直接发送 PUT 之类的请求还要封装请求体中的数据
    // 配置上 HttpPutFormContentFilter
    // 他的作用，将请求体中的数据解析包装成一个map。
    // request 被重新包装，request.getParameter() 被重写，就会从自己封装的map中取数据
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    public Msg saveEmp(Employee employee) {
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public Msg getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }

    @RequestMapping("/check-user")
    @ResponseBody
    public Msg checkUser(String empName) {
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regex)) {
            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }
        boolean b = employeeService.checkUser(empName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "用户名不可用");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                logger.info("错误的字段名：" + fieldError.getField());
                logger.info("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    @ResponseBody
    @RequestMapping("/emps")
    public Msg getEmpsAjax(@RequestParam(value = "pn", defaultValue = "1") Integer pageNo, HttpServletRequest request) {
//        // 引入PageHelper 分页插件
//        // 在查询之前只需要调用，传入页码，以及每页的大小
//        PageHelper.startPage(pageNo, 5);
//        // startPage 后面紧跟的这个查询就是一个分页查询
//        List<Employee> emps = employeeService.getAll();
//        // 使用 pageInfo 包装查询后的结果，只需要将 pageInfo 交给页面就行了
//        // 封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
//        PageInfo<Employee> pageInfo = new PageInfo<>(emps, 5);
//        request.setAttribute("pageInfo", pageInfo);
        Page<Employee> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(5);

        IPage<Employee> pageInfo = employeeService.getPage(page, Wrappers.emptyWrapper());
        request.setAttribute("pageInfo", pageInfo);

        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping("/emp-list")
    public String empList(@RequestParam(value = "pn", defaultValue = "1") Integer pageNo,
                          Model model) {
//        // 引入PageHelper 分页插件
//        // 在查询之前只需要调用，传入页码，以及每页的大小
//        PageHelper.startPage(pageNo, 5);
//        // startPage 后面紧跟的这个查询就是一个分页查询
//        List<Employee> emps = employeeService.getAll();
//        // 使用 pageInfo 包装查询后的结果，只需要将 pageInfo 交给页面就行了
//        // 封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
//        PageInfo<Employee> pageInfo = new PageInfo<>(emps, 5);
//        model.addAttribute("pageInfo", pageInfo);
        Page<Employee> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(5);

        IPage<Employee> pageInfo = employeeService.getPage(page, Wrappers.emptyWrapper());
        model.addAttribute("pageInfo", pageInfo);

        return "list";
    }

    @RequestMapping("/emp-index")
    public String empIndex() {
        return "index";
    }
}
