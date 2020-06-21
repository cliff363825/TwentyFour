package com.onevgo.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
 * 用于Spring安全性的自动配置。
 * 提供基于绑定到SecurityProperties bean的配置的AuthenticationManager。
 * 有一个用户(名为“user”)的密码是随机的，并在启动期间打印在INFO级别的控制台上。
 * 在web应用程序中，这种配置还使用HTTP基本安全性保护所有web端点(一些众所周知的静态资源位置除外)。
 * 要替换web应用程序中的所有默认行为，请使用@EnableWebSecurity提供@Configuration。
 * 要在缺省值之前添加您自己的应用程序安全层，请添加WebSecurityConfigurerAdapter类型的@Configuration。
 */
@SpringBootApplication
public class SpringBootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }

}
