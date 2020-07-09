package com.onevgo.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.onevgo.shardingjdbc.mapper")
public class ShardingJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcApplication.class, args);
	}

}
