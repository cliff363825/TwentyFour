package com.onevgo.spring.annotation.config;

import com.onevgo.spring.annotation.bean.Department;
import com.onevgo.spring.annotation.bean.Employee;
import com.onevgo.spring.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 配置类
 * 1. @Configuration 告诉 spring 这是一个配置类
 * <p>
 * 2. @ComponentScan
 * <ul>
 * <li>1. value 指定要扫描的包</li>
 * <li>2. excludeFilters = Filter[] 指定扫描的时候按照什么规则排除那些组件</li>
 * <li>3. includeFilters = Filter[] 指定扫描的时候只需要包含哪些组件</li>
 * <li>4. FilterType.ANNOTATION 按照注解</li>
 * <li>5. FilterType.ASSIGNABLE_TYPE 按照给定的类型</li>
 * <li>6. FilterType.ASPECTJ 使用 aspectj 表达式</li>
 * <li>7. FilterType.REGEX 使用正则表达式</li>
 * <li>8. FilterType.CUSTOM 使用自定义规则</li>
 * </ul>
 */
@Configuration
@ComponentScan(value = "com.onevgo.spring.annotation",
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class})
//        },
//        includeFilters = {
//            @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Service.class, Repository.class}),
//            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
//            @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
//        },
        useDefaultFilters = false
)
public class MainConfig {
    /**
     * 给容器中注册一个 Bean，类型为返回值的类型，id 默认是用方法名作为id
     *
     * @return
     */
    @Bean("person")
    public Person person01() {
        return new Person("lisi", 20);
    }

    @Bean
    public Department department() {
        System.out.println(this.getClass().getName());
        Department department = new Department();
        department.setName("研发部");
        return department;
    }

    @Bean
    public Employee employee() {
        Employee employee = new Employee();
        employee.setName("张三");
        employee.setDepartment(department());
        return employee;
    }
}
