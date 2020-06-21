package com.onevgo.mvc.dao.impl;

import com.onevgo.mvc.dao.CriteriaCustomer;
import com.onevgo.mvc.dao.CustomerDAO;
import com.onevgo.mvc.domain.Customer;

import java.util.List;

public class CustomerDAOXMLImpl implements CustomerDAO {
    @Override
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer criteriaCustomer) {
        System.out.println("CustomerDAOXMLImpl.getForListWithCriteriaCustomer");
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public Customer get(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public long getCountWithName(String name) {
        return 0;
    }
}
