package com.onevgo.functions;

public class Getcwd {
    public static String getcwd() {
        //return Paths.get("").toAbsolutePath().toString();
        return System.getProperty("user.dir");
    }

    public static void main(String[] args) {
        System.out.println(getcwd());
    }
}
