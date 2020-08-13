package com.onevgo.functions;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class GetObjectVars {
    public static Map<String, Object> getObjectVars(Object o) {
        Map<String, Object> vars = new HashMap<>();
        Field[] fields = o.getClass().getFields();
        for (Field field : fields) {
            try {
                vars.put(field.getName(), field.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return vars;
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        System.out.println(getObjectVars(foo));
    }

    static class Foo {
        private Object a;
        public int b = 1;
        public Object c;
        private Object d;
        static Object e;
    }
}
