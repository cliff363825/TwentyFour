package com.onevgo.servlet.v3.springmvc;

import com.onevgo.servlet.v3.springmvc.config.AppConfig;
import com.onevgo.servlet.v3.springmvc.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web容器启动的时候创建对象，调用方法来初始化容器以前前端控制器
 */
public class MyWevAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获得根容器的配置类，（Spring的配置文件）父容器
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 获取web 容器的配置类（SpringMVC配置文件）子容器
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * 获取DispatcherServlet的映射信息
     * / 拦截所有请求（包括静态资源（xx.js,xx.png）），但是不包括 *.jsp
     * /* 拦截所有请求，连 *.jsp 页面都拦截，jsp页面是 tomcat 的jsp引擎解析的
     * 精确匹配(=) > 最长前缀匹配(/a/b/c/*) > 后缀匹配(*.jsp) > 默认匹配(/)
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
