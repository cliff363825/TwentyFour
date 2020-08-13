package com.onevgo.functions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GetClassVars {
    private final static Map<Class, Map<String, Object>> CLASS_VARS_MAP = new ConcurrentHashMap<>();

    public static Map<String, Object> getClassVars(Class<?> clazz) {
        if (!CLASS_VARS_MAP.containsKey(clazz)) {
            Map<String, Object> vars = new HashMap<>();
            try {
                Object o = clazz.newInstance();
                vars = GetObjectVars.getObjectVars(o);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            CLASS_VARS_MAP.put(clazz, vars);
        }

        return CLASS_VARS_MAP.get(clazz);
    }

    public static void main(String[] args) {
        TestCase.expose();

        System.out.println("-----------------");
        System.out.println(String.join(",", getClassVars(TestCase.class).keySet()));
    }

    static class TestCase {
        public int a = 1;
        protected int b = 2;
        private int c = 3;

        public static void expose() {
            System.out.println(String.join(",", getClassVars(TestCase.class).keySet()));
        }
    }
}

