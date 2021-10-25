package com.onevgo.j2se.reflection;

public class ExampleA extends SuperExample<String> {
    static {
//        System.out.println("ExampleA静态代码块执行，v4=" + v4); // Illegal forward reference
        System.out.println("ExampleA静态代码块执行，v4=" + getV4()); // Illegal forward reference
        v4 = 5;
    }

    private static int v4 = 4;
    private static int v5 = v4;
    public static final int M = 1;

    public static int getV4() {
        return v4;
    }
}
