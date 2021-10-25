package com.onevgo.j2se.reflection;

public class SuperExample<T> {
//    private static int v1 = v2; // Illegal forward reference
    private static int v1 = getV2();
    private static int v2 = 2;
    private static int v3 = getV2();

    static {
        System.out.println("SuperExample静态代码块执行, v1=" + v1 + ", v2=" + v2 + ", v3=" + v3);
    }

    public static int getV2() {
        return v2;
    }
}
