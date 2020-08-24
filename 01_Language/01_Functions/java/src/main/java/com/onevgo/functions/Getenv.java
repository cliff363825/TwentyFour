package com.onevgo.functions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Getenv {
    public static String getenv(String var) {
        return System.getProperty(var);
    }

    public static Properties getenv() {
        return System.getProperties();
    }

    public static void main(String[] args) {
        System.out.println(getenv());
    }
}
