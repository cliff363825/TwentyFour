package com.onevgo.functions;

public class ClassExists {
    public static boolean classExists(String className) {
        return classExists(className, true);
    }

    public static boolean classExists(String className, boolean initialize) {
        return classExists(className, initialize, ClassExists.class.getClassLoader());
    }

    public static boolean classExists(String className, boolean initialize, ClassLoader loader) {
        try {
            Class.forName(className, initialize, loader);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(classExists("MyClass", false));
    }
}
