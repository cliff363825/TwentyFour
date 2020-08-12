package com.onevgo.functions;

public class GetIncludePath {
    public static String getIncludePath() {
        return System.getProperty("java.class.path");
    }

    public static void main(String[] args) {
        System.out.println(getIncludePath());
    }
}
