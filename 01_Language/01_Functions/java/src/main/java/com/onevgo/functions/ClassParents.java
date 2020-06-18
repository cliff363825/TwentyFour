package com.onevgo.functions;

import java.util.ArrayList;
import java.util.List;

public class ClassParents {
    public static List<Class<?>> classParents(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return classParents(clazz);
    }

    public static List<Class<?>> classParents(Class<?> clazz) {
        List<Class<?>> res = new ArrayList<>();
        Class<?> superclass = clazz.getSuperclass();
        while (superclass != null && superclass != Object.class) {
            res.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(classParents("com.onevgo.functions.C"));
    }
}
