package com.onevgo.mvc.dao.factory;

import com.onevgo.mvc.dao.CustomerDAO;
import com.onevgo.mvc.dao.impl.CustomerDAOJdbcImpl;
import com.onevgo.mvc.dao.impl.CustomerDAOXMLImpl;

import java.util.HashMap;
import java.util.Map;

public class CustomerDAOFactory {
    private Map<String, CustomerDAO> daos = new HashMap<>();

    private final static CustomerDAOFactory instance = new CustomerDAOFactory();

    private CustomerDAOFactory() {
        daos.put("jdbc", new CustomerDAOJdbcImpl());
        daos.put("xml", new CustomerDAOXMLImpl());
    }

    public static CustomerDAOFactory getInstance() {
        return instance;
    }

    private static String type;

    public void setType(String type) {
        CustomerDAOFactory.type = type;
    }

    public CustomerDAO getCustomerDAO() {
        return daos.get(type);
    }
}
