package com.onevgo.spring.jdbc;

import com.onevgo.spring.jdbc.Customer;
import com.onevgo.spring.jdbc.CustomerDAO;
import com.onevgo.spring.jdbc.CustomerDAO2;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class JdbcTest {
    private ApplicationContext applicationContext;
    private JdbcTemplate jdbcTemplate;
    private CustomerDAO customerDAO;
    private CustomerDAO2 customerDAO2;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        customerDAO = applicationContext.getBean(CustomerDAO.class);
        customerDAO2 = applicationContext.getBean(CustomerDAO2.class);
        namedParameterJdbcTemplate = applicationContext.getBean(NamedParameterJdbcTemplate.class);
    }

    @Test
    public void testInsert1() {
        String sql = "insert into customer (name, email, birth) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowCount = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, "onevgo.com");
                ps.setString(2, "onevgo@onevgo.com");
                ps.setDate(3, new Date(System.currentTimeMillis()));
                return ps;
            }
        }, keyHolder);
        System.out.println(rowCount + " ---- " + keyHolder.getKey().intValue());
    }

    @Test
    public void testInsert2() {
        String sql = "insert into customer (name, email, birth) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowCount = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, "onevgo.com");
                ps.setString(2, "onevgo@onevgo.com");
                ps.setDate(3, new Date(System.currentTimeMillis()));
                return ps;
            }
        }, keyHolder);
        System.out.println(rowCount + " ---- " + keyHolder.getKey().intValue());
    }

    @Test
    public void testInsert3() {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("customer").usingGeneratedKeyColumns("id");
        Map<String, Object> args = new HashMap<>();
        args.put("name", "test_name");
        args.put("email", "test_name@onevgo.com");
        args.put("birth", new Date(System.currentTimeMillis()));
        Number newId = simpleJdbcInsert.executeAndReturnKey(args);
        long l = newId.longValue();
        System.out.println("id => " + l);
    }

    /**
     * 执行 INSERT UPDATE DELETE
     */
    @Test
    public void testUpdate() {
        String sql = "update customer set name=? where id=?";
        int rowCount = jdbcTemplate.update(sql, "onevgo", 300010);
        System.out.println(rowCount);
    }

    /**
     * 执行批量更新： 批量的 INSERT, UPDATE, DELETE
     * 最后一个参数时 Object[] 的 List 类型，因为修改一条记录需要一个 Object 的数组，那么多条不就需要多个 Object 的数组吗
     */
    @Test
    public void testBatchUpdate() {
        String sql = "insert into customer(name,email,birth) values(?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Object[] row = new Object[3];
            row[0] = "name: " + i;
            row[1] = "email: " + i;
            row[2] = new Date(System.currentTimeMillis());

            batchArgs.add(row);
        }
        int[] rowCount = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(rowCount));
    }

    /**
     * 从数据库中获取一条记录，实际得到对应的一个对象
     * 注意不是调用 queryForObject(String sql, Class<Customer> requiredType, Object... args) 方法！
     * 而需要调用 queryForObject(String sql, RowMapper<Customer> rowMapper, Object... args)
     * 1. 其中 RowMapper 制定如何去映射结果集的行，常用的实现类为 BeanPropertyRowMapper
     * 2. 使用 SQL 中列的别名完成列名和类的属性名的映射。 例如 last_name as lastName
     * 3. 不支持级联属性，JdbcTemplate 到底是一个 JDBC 的小工具，而不是 ORM 框架
     */
    @Test
    public void testQueryForObject() {
        // 1. 返回结果集是 一行
        // 1.1 一行多列
        String sql = "select id,name,email,birth from customer where id=?";
        Customer customer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), 30010);
        System.out.println(customer);

        // 1.2. 一行一列
        sql = "select name from customer where id=?";
        String name = jdbcTemplate.queryForObject(sql, String.class, 30010);
        // 实际上调用的是 jdbcTemplate.queryForObject(sql, new SingleColumnRowMapper<>(String.class), 30010);
        System.out.println(name);
    }

    /**
     * 查询实体类的集合
     * 注意条用的不是 queryForList 方法
     */
    @Test
    public void testQueryForList() {
        // 1. 返回结果集是多行
        // 1.1 多行多列
        String sql = "select id,name,email,birth from customer limit ?,?";
        List<Customer> customers = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class), 0, 20);
        System.out.println(customers);

        // 1.2 多行单列
        sql = "select name from customer limit ?,?";
        List<String> names = jdbcTemplate.query(sql, new SingleColumnRowMapper<>(String.class), 0, 20);
        System.out.println(names);
    }

    /**
     * 获取单个列的值，或做统计查询
     * 使用 queryForObject(String sql, Class<Long> requiredType)
     */
    @Test
    public void testQueryForObject2() {
        // 1. 返回结果集是 一行一列
        String sql = "select count(*) from customer";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(count);
    }

    @Test
    public void testCustomerDAO() {
        System.out.println(customerDAO.get(1));
    }

    @Test
    public void testCustomerDAO2() {
        System.out.println(customerDAO2.get(1));
    }

    /**
     * 可以为参数起名字，
     * 1. 好处： 若有多个参数，则不用再去对应位置，直接对应参数名，便于维护
     * 2. 缺点： 较为麻烦。
     */
    @Test
    public void testNamedParameterJdbcTemplate() {
        String sql = "insert into customer(name, email, birth) values (:name, :email, :birth)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "onevgo.com");
        paramMap.put("email", "123@onevgo.com");
        paramMap.put("birth", new Date(System.currentTimeMillis()));
        int rowCount = namedParameterJdbcTemplate.update(sql, paramMap);
        System.out.println(rowCount);
    }

    /**
     * 使用具名参数时，可以使用 update(String sql, SqlParameterSource paramSource) 方法进行更新操作
     * 1. SQL 语句中的参数名和类的属性一致！
     * 2. 使用 SqlParameterSource 的 BeanPropertySqlParameterSource 实现类作为参数
     */
    @Test
    public void testNamedParameterJdbcTemplate2() {
        String sql = "insert into customer(name, email, birth) values (:name, :email, :birth)";
        Customer customer = new Customer();
        customer.setName("onevgo.com");
        customer.setEmail("222@onevgo.com");
        customer.setBirth(new Date(System.currentTimeMillis()));
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(customer);
        int rowCount = namedParameterJdbcTemplate.update(sql, paramSource);
        System.out.println(rowCount);
    }

    @Test
    public void testDataSource() {
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource);
    }
}
