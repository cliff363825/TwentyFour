package com.onevgo.functions;

public class InterfaceExists {
    public static boolean interfaceExists(String interfaceName) {
        return interfaceExists(interfaceName, true);
    }

    public static boolean interfaceExists(String interfaceName, boolean autoload) {
        return interfaceExists(interfaceName, autoload, InterfaceExists.class.getClassLoader());
    }

    public static boolean interfaceExists(String interfaceName, boolean autoload, ClassLoader classLoader) {
        try {
            Class<?> clazz = Class.forName(interfaceName, autoload, classLoader);
            return clazz.isInterface();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(interfaceExists("java.io.Serializable"));
    }
}
