package com.onevgo.functions;

import java.util.*;

public class ClassImplements {
    public static Collection<Class<?>> classImplements(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return classImplements(clazz);
    }

    public static Collection<Class<?>> classImplements(Class<?> clazz) {
        Collection<Class<?>> res = new LinkedHashSet<>();
        for (Class<?> iter : clazz.getInterfaces()) {
            res.add(iter);
            res.addAll(classImplements(iter));
        }

        Class<?> superclass = clazz.getSuperclass();
        while (superclass != null && superclass != Object.class) {
            res.addAll(classImplements(superclass));
            superclass = superclass.getSuperclass();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(classImplements("com.onevgo.functions.C"));
    }
}
