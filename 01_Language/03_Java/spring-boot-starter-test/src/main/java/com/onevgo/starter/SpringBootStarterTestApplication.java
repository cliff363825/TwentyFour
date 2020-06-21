package com.onevgo.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.onevgo.controller"})
public class SpringBootStarterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterTestApplication.class, args);
    }

}
