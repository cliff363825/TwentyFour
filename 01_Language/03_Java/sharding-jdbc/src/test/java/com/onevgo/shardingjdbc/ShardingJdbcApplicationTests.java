package com.onevgo.shardingjdbc;

import com.onevgo.shardingjdbc.entity.Course;
import com.onevgo.shardingjdbc.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingJdbcApplicationTests {

	// 注入mapper
	@Autowired
	private CourseMapper courseMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void addCourse() {
		Course course = new Course();
		course.setCname("java");
		course.setUserId(100L);
		course.setCstatus("Normal");
		courseMapper.insert(course);
	}
}
