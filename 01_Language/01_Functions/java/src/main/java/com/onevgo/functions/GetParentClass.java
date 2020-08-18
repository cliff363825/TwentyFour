package com.onevgo.functions;

public class GetParentClass {
    public static Class getParentClass(Class clazz) {
        return clazz.getSuperclass();
    }

    public static void main(String[] args) {
        new child();
    }

    static class dad {
        public dad() {
            // implements some logic
        }
    }

    static class child extends dad {
        public child() {
            System.out.println("I'm " + getParentClass(child.class) + "'s son\n");
        }
    }
}
