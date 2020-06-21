package com.onevgo.servlet.v3.springmvc.config;

import com.onevgo.servlet.v3.springmvc.controller.MyFirstInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * SpringMVC只扫描controller,子容器
 * useDefaultFilters = false，禁用默认的过滤规则
 */
@ComponentScan(value = "com.onevgo.servlet.v3.springmvc", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
}, useDefaultFilters = false)
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
    /**
     * 视图解析器
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 默认所有的页面都从 /WEB-INF/xxx.jsp
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    /**
     * 静态资源访问
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
    }
}
