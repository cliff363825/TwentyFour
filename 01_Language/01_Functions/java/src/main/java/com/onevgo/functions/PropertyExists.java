package com.onevgo.functions;

import cn.hutool.core.util.ReflectUtil;

public class PropertyExists {
    public static boolean propertyExists(Class<?> clazz, String property) {
        return ReflectUtil.getField(clazz, property) != null;
    }

    static class MyClass {
        public String mine;
        private String xpto;
        static protected String test;
    }

    public static void main(String[] args) {
        System.out.println(propertyExists(MyClass.class, "mine"));
        System.out.println(propertyExists(MyClass.class, "xpto"));
        System.out.println(propertyExists(MyClass.class, "bar"));
        System.out.println(propertyExists(MyClass.class, "test"));
    }
}


