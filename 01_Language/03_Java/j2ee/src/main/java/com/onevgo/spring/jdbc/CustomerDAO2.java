package com.onevgo.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CustomerDAO2 extends JdbcDaoSupport {
    @Autowired
    @Qualifier(value = "dataSource")
    public void setDataSource2(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public Customer get(Integer id) {
        String sql = "select id,name,email,birth from customer where id=?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer customer = getJdbcTemplate().queryForObject(sql, rowMapper, id);
        return customer;
    }
}
