package com.onevgo.springboot;

import com.onevgo.springboot.entity.TbUser;
import com.onevgo.springboot.repository.yii2.TbUserRepository;
import com.onevgo.springboot.repository.def.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * SpringBoot 单元测试
 * <p>
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
    // 记录器
    private static Logger logger = LoggerFactory.getLogger(SpringbootApplicationTests.class);

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DataSource dataSource;
    @Autowired
    @Qualifier("yii2DataSource")
    private DataSource yii2DataSource;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testLogger() {
        // 日志的级别
        // 由低到高 trace<debug<info<warn<error
        // 可以调整输出的日志级别；日志就只会在这个级别以后的高级别生效
        logger.trace("这是trace日志。。。");
        logger.debug("这是debug日志。。。");
        // SpringBoot默认给我们的使用的是info级别，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("这是info日志。。。");
        logger.warn("这是warn日志。。。");
        logger.error("这是error日志。。。");
    }

    @Test
    public void testHelloService() {
        boolean b = applicationContext.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        ResultSet resultSet = connection.createStatement().executeQuery("show tables");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

        connection = yii2DataSource.getConnection();
        System.out.println(connection);
        resultSet = connection.createStatement().executeQuery("show tables");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }

    @Autowired
    private TbUserRepository tbUserRepository;

    @Test
    public void testJpa() {
        Specification<TbUser> spec = new Specification<TbUser>() {
            @Override
            public Predicate toPredicate(Root<TbUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                predicates.add(cb.equal(root.get("id"), 1));
                predicates.add(cb.equal(root.get("status"), 10));

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
        List<TbUser> tbUserList = tbUserRepository.findAll(spec);
        System.out.println("tbUserList = " + tbUserList);
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testJpa2() {
        userRepository.findOne(1);
    }
}
