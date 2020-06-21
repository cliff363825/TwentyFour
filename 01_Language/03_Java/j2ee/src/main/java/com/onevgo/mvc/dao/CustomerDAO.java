package com.onevgo.mvc.dao;

import com.onevgo.mvc.domain.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer criteriaCustomer);

    public List<Customer> getAll();

    public void update(Customer customer);

    public void save(Customer customer);

    public Customer get(Integer id);

    public void delete(Integer id);

    /**
     * 返回和 name 相等的记录数
     *
     * @param name
     * @return
     */
    public long getCountWithName(String name);
}
