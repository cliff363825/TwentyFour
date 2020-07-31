package com.onevgo.functions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GetClassVars {
    public static Field[] getClassVars(Class<?> clazz) {
        return clazz.getFields();
    }

    public static void main(String[] args) {
        TestCase.expose();

        System.out.println("-----------------");
        System.out.println(Arrays.stream(getClassVars(TestCase.class)).map(Field::getName).collect(Collectors.joining(",")));
    }

    static class TestCase {
        public int a = 1;
        protected int b = 2;
        private int c = 3;

        public static void expose() {
            System.out.println(Arrays.stream(getClassVars(TestCase.class)).map(Field::getName).collect(Collectors.joining(",")));
        }
    }
}

