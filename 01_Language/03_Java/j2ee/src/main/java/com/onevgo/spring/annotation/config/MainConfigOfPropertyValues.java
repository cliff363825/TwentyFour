package com.onevgo.spring.annotation.config;

import com.onevgo.spring.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 使用 @PropertySource 读取外部配置文件中的 k/v 保存到运行的环境变量中；
 * 加载完外部的配置文件以后使用 ${} 取出配置文件的值
 */
@PropertySource(value = {"classpath:/spring-annotation-person.properties"})
@Configuration
public class MainConfigOfPropertyValues {
    @Bean
    public Person person() {
        return new Person();
    }
}
