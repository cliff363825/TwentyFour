package com.onevgo.upload.listeners;

import com.onevgo.upload.utils.AppFileUploadProperties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class AppFileUploadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InputStream is = getClass().getResourceAsStream("/upload.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String propertyName = (String) propertyNames.nextElement();
                AppFileUploadProperties.getInstance().setConfig(propertyName, properties.getProperty(propertyName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
