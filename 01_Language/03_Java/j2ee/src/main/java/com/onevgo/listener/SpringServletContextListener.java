package com.onevgo.listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 1. 获取 Spring 配置文件的名称
        ServletContext servletContext = sce.getServletContext();
        String configLocation = servletContext.getInitParameter("configLocation");

        // 2. 创建 IOC 容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        servletContext.setAttribute("ApplicationContext", applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
