package com.onevgo.functions;

public class IsA {
    public static boolean isA(Object object, String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.isInstance(object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        class WidgetFactory {
            public String oink = "moo";
        }

        WidgetFactory WF = new WidgetFactory();
//        System.out.println(WidgetFactory.class.getName());
        if (isA(WF, "com.onevgo.functions.IsA$1WidgetFactory")) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
