package com.onevgo.springboot;

import com.onevgo.springboot.component.MyApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <code>@SpringBootApplication</code> 来标注一个主程序类，说明这是一个Spring Boot应用
 * 1. @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
 */
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootApplication.class);
//        Banner.Mode bannerMode = Banner.Mode.OFF;
//        springApplication.setBannerMode(bannerMode);
        springApplication.addListeners(new MyApplicationListener());
        springApplication.run(args);
    }

}
