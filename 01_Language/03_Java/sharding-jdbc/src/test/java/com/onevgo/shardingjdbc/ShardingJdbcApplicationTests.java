package com.onevgo.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.onevgo.shardingjdbc.entity.Course;
import com.onevgo.shardingjdbc.entity.Udict;
import com.onevgo.shardingjdbc.entity.User;
import com.onevgo.shardingjdbc.mapper.CourseMapper;
import com.onevgo.shardingjdbc.mapper.UdictMapper;
import com.onevgo.shardingjdbc.mapper.UserMapper;
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

	// 测试水平分表
	@Test
	void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("java" + i);
            course.setUserId(100L);
            course.setCstatus("Normal");
            courseMapper.insert(course);
        }
	}

    @Test
    void findCourse() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", 488066537526132736L);
        Course course = courseMapper.selectOne(queryWrapper);
        System.out.println(course);
    }

    // 测试水平分库

    @Test
    void addCourseDb() {
        Course course = new Course();
        course.setCname("javademo");
        course.setUserId(111L);
        course.setCstatus("Normal");
        courseMapper.insert(course);
    }

    @Test
    void findCourseDb() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", 100L);
        queryWrapper.eq("cid", 488730878378246145L);
        Course course = courseMapper.selectOne(queryWrapper);
        System.out.println(course);
    }

    // 测试垂直分库
    @Autowired
    private UserMapper userMapper;

    @Test
    void addUserDb() {
        User user = new User();
        user.setUsername("lucy");
        user.setUstatus("a");

        userMapper.insert(user);
    }

    @Test
    void findUserDb() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", 488991635519045633L);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    // 测试公共表
    @Autowired
    private UdictMapper udictMapper;

    @Test
    void addDict() {
        Udict udict = new Udict();
        udict.setUstatus("a");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
    }

    @Test
    void deleteDict() {
        QueryWrapper<Udict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dictid", 489037208867569665L);
        udictMapper.delete(queryWrapper);
    }
}
