package com.onevgo.springboot.cache.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.onevgo.springboot.cache.mapper")
public class MyBatisConfig {
}
