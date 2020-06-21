package com.onevgo.mvc;

import com.onevgo.mvc.dao.CriteriaCustomer;
import com.onevgo.mvc.dao.CustomerDAO;
import com.onevgo.mvc.dao.impl.CustomerDAOJdbcImpl;
import com.onevgo.mvc.domain.Customer;
import org.junit.Test;

import java.util.List;

public class TestCustomerDaoJdbcImpl {
    private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();

    @Test
    public void testGetForListWithCriteriaCustomer() {
        List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(new CriteriaCustomer("J", "x", null));
        System.out.println(customers);
    }

    @Test
    public void testGetAll() {
        List<Customer> customers = customerDAO.getAll();
        System.out.println(customers);
    }

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setAddress("Shanghai");
        customer.setName("Jerry");
        customer.setPhone("13900000000");

        customerDAO.save(customer);
    }

    @Test
    public void testGet() {
        Customer customer = customerDAO.get(1);
        System.out.println(customer);
    }

    @Test
    public void testDelete() {
        customerDAO.delete(1);
    }

    @Test
    public void testGetCountWithName() {
        long count = customerDAO.getCountWithName("Jerry");
        System.out.println(count);
    }
}
