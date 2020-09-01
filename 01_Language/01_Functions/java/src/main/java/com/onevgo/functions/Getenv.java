package com.onevgo.functions;

import java.util.Map;

public class Getenv {
    public static String getenv(String var) {
        return System.getenv(var);
    }

    public static Map<String, String> getenv() {
        return System.getenv();
    }

    public static void main(String[] args) {
        System.out.println(getenv());
    }
}
