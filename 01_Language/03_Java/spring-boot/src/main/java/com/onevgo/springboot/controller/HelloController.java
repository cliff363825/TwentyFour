package com.onevgo.springboot.controller;

import com.onevgo.springboot.bean.Person;
import com.onevgo.springboot.component.DateEditor;
import com.onevgo.springboot.exception.UserNotExistException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.*;

// 这个类的所有方法返回的数据直接写给浏览器（如果是对象转为 json 数据）
/*
@ResponseBody
@Controller
*/
@Controller
public class HelloController {
    @Value("${person.last-name}")
    private String lastName;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello world " + lastName;
    }

    @RequestMapping("/success")
    public String success(Model model) {
        model.addAttribute("hello", "<h1>你好</h1>");
        model.addAttribute("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        // classpath:/templates/
        return "success";
    }

    @ResponseBody
    @RequestMapping("/hello2")
    public String hello2(@RequestParam("user") String user) throws Exception {
        if ("404".equals(user)) {
            // ExceptionHandlerExceptionResolver
            throw new UserNotExistException();
        } else if ("405".equals(user)) {
            // DefaultHandlerExceptionResolver
            throw new HttpRequestMethodNotSupportedException("405");
        } else if ("500".equals(user)) {
            throw new RuntimeException();
        } else {
            return "success";
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ResponseBody
    @RequestMapping("/hello3")
    public Map<String, Object> hello3(Model model) {
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from department limit 1");
        return map;
    }

    @Autowired
    @Qualifier("yii2JdbcTemplate")
    private JdbcTemplate yii2JdbcTemplate;

    @ResponseBody
    @RequestMapping("/hello4")
    public Map<String, Object> hello4() {
        Map<String, Object> map = yii2JdbcTemplate.queryForMap("select * from tb_post limit 1");
        return map;
    }

    @Autowired
    private Person person;
    @Autowired
    private ApplicationContext applicationContext;

    @ResponseBody
    @RequestMapping("/hello5")
    public Person hello5() {
        return person;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @ResponseBody
    @RequestMapping("/hello6")
    public Long hello6(@RequestParam("date") Date date) {
        return date.getTime();
    }
}
