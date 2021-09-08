package com.onevgo.j2se.collection;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesMain {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertiesMain.class.getResourceAsStream("/jdbc.properties"));
        log.info("{}", properties.getProperty("user"));
        log.info("{}", properties.getProperty("password"));
    }
}
