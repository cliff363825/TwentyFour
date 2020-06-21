package com.onevgo.mvc.dao.impl;

import com.onevgo.mvc.dao.CriteriaCustomer;
import com.onevgo.mvc.dao.CustomerDAO;
import com.onevgo.mvc.dao.DAO;
import com.onevgo.mvc.domain.Customer;

import java.util.List;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO {
    @Override
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer criteriaCustomer) {
        String sql = "SELECT id, name, address, phone FROM customers WHERE " +
                " name LIKE ? AND address LIKE ? AND phone like ?";
        return getForList(sql, criteriaCustomer.getName(), criteriaCustomer.getAddress(), criteriaCustomer.getPhone());
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT id, name, address, phone FROM customers";
        return getForList(sql);
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customers SET name = ?, address = ?, phone = ? WHERE id = ?";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone(), customer.getId());
    }

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customers(name, address, phone) VALUES (?,?,?)";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone());
    }

    @Override
    public Customer get(Integer id) {
        String sql = "SELECT id, name, address, phone FROM customers WHERE id=?";
        return get(sql, id);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        update(sql, id);
    }

    @Override
    public long getCountWithName(String name) {
        String sql = "SELECT COUNT(id) FROM customers WHERE name = ?";
        return getForValue(sql, name);
    }
}
