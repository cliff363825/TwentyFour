package com.onevgo.spring.struts2.servlet;

import com.onevgo.spring.struts2.beans.Person;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 从 application 域对象中得到 IOC 容器的引用
        ApplicationContext applicationContext = (ApplicationContext) getServletContext().getAttribute("ApplicationContext");

        // 2. 从 IOC 容器中得到需要的 bean
        Person person = applicationContext.getBean(Person.class);
        person.hello();
    }
}
