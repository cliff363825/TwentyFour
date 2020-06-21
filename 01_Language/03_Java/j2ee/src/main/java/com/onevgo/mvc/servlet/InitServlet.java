package com.onevgo.mvc.servlet;

import com.onevgo.mvc.dao.factory.CustomerDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InitServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        CustomerDAOFactory.getInstance().setType("jdbc");
        InputStream is = getClass().getResourceAsStream("/switch.properties");
        Properties properties = new Properties();
        if (is != null) {
            try {
                properties.load(is);
                String type = properties.getProperty("type");
//                System.out.println(type);
                CustomerDAOFactory.getInstance().setType(type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
