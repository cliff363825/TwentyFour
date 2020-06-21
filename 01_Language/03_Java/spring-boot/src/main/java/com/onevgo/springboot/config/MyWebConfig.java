package com.onevgo.springboot.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.onevgo.springboot.component.*;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public class MyWebConfig {
    /**
     * 使用WebMvcConfigurerAdapter扩展SpringMVC的功能
     * 1. @Configuration标注的配置类继承 WebMvcConfigurerAdapter
     */
    @Configuration
    protected static class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            // 浏览器发送 /success2 请求来到 success
            registry.addViewController("/success2").setViewName("success");
        }
    }

    /**
     * 使用WebMvcConfigurerAdapter扩展SpringMVC的功能
     * 2. @Bean标注的方法往容器中注册WebMvcConfigurerAdapter组件
     */
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter1() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
    }

    // @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter2() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 静态资源 *.css *.js
                // springboot 已经做好了静态资源映射
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/user/login");
            }
        };
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter3() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/hello");
            }
        };
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    /**
     * 配置 druid 的监控
     * 1. 配置一个管理后台的servlet
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.addUrlMappings("/druid/*");

        Map<String, String> initParameters = new HashMap<>();
        initParameters.put(StatViewServlet.PARAM_NAME_USERNAME, "admin");
        initParameters.put(StatViewServlet.PARAM_NAME_PASSWORD, "123456");
        initParameters.put(StatViewServlet.PARAM_NAME_ALLOW, "localhost"); // 默认就是允许所有
        initParameters.put(StatViewServlet.PARAM_NAME_DENY, "192.168.1.186");
        registrationBean.setInitParameters(initParameters);

        return registrationBean;
    }

    /**
     * 2. 配置一个web监控的filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());
//        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.addUrlPatterns("/*");

        Map<String, String> initParameters = new HashMap<>();
        initParameters.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
        registrationBean.setInitParameters(initParameters);

        return registrationBean;
    }

    /**
     * 注册servlet
     */
    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new MyServlet());
//        registrationBean.setUrlMappings(Arrays.asList("/myServlet"));
        registrationBean.addUrlMappings("/myServlet");

        return registrationBean;
    }

    /**
     * 注册filter
     */
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));

        return registrationBean;
    }

    /**
     * 注册listener
     */
    @Bean
    public ServletListenerRegistrationBean myListener() {
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new MyListener());

        return registrationBean;
    }

    /**
     * 如果需要以编程方式配置嵌入式servlet容器，
     * 可以注册一个实现EmbeddedServletContainerCustomizer接口的Spring bean。
     * EmbeddedServletContainerCustomizer提供对ConfigurableEmbeddedServletContainer的访问，
     * 该容器包含许多定制setter方法。
     *
     * @see <a href="https://docs.spring.io/spring-boot/docs/1.5.21.RELEASE/reference/htmlsingle/#boot-features-programmatic-embedded-container-customization">Programmatic customization</a>
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setPort(9000);
            }
        };
    }

    /**
     * 直接定制化嵌入式容器
     */
    // @Bean
    public EmbeddedServletContainerFactory servletContainerFactory() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(9000);
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        return factory;
    }
}
