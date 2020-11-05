package com.onevgo.functions;

public class IsSubclassOf {
    public static boolean isSubclassOf(Object object, Class clazz) {
        if (object == null || clazz == null) {
            return false;
        }
        return isSubclassOf(object.getClass(), clazz);
    }

    public static boolean isSubclassOf(Class object, Class clazz) {
        if (object == null || clazz == null) {
            return false;
        }
        return !clazz.equals(object) && clazz.isAssignableFrom(object);
    }

    public static void main(String[] args) {
        class WidgetFactory {
            public String oink = "moo";
        }

        class WidgetFactory_Child extends WidgetFactory {
            public String oink = "oink";
        }

        WidgetFactory WF = new WidgetFactory();
        WidgetFactory_Child WFC = new WidgetFactory_Child();
        if (isSubclassOf(WFC, WidgetFactory.class)) {
            System.out.println("yes, WFC is a subclass of WidgetFactory");
        } else {
            System.out.println("no, WFC is not a subclass of WidgetFactory");
        }

        if (isSubclassOf(WF, WidgetFactory.class)) {
            System.out.println("yes, WF is a subclass of WidgetFactory");
        } else {
            System.out.println("no, WF is not a subclass of WidgetFactory");
        }

        if (isSubclassOf(WidgetFactory_Child.class, WidgetFactory.class)) {
            System.out.println("yes, WidgetFactory_Child is a subclass of WidgetFactory");
        } else {
            System.out.println("no, WidgetFactory_Child is not a subclass of WidgetFactory");
        }

        MyClass myObject = new MyClass();
        if (isSubclassOf(myObject, MyInterface.class)) {
            System.out.println("Yes, my_object is a subclass of MyInterface");
        } else {
            System.out.println("No, my_object is not a subclass of MyInterface");
        }

        if (isSubclassOf(MyClass.class, MyInterface.class)) {
            System.out.println("Yes, MyClass is a subclass of MyInterface");
        } else {
            System.out.println("No, MyClass is not a subclass of MyInterface");
        }

        if (isSubclassOf(int.class, Number.class)) {
            System.out.println("Yes, int is a subclass of Number");
        } else {
            System.out.println("No, int is not a subclass of Number");
        }

        if (isSubclassOf(Integer.class, Number.class)) {
            System.out.println("Yes, Integer is a subclass of Number");
        } else {
            System.out.println("No, Integer is not a subclass of Number");
        }
    }

    private interface MyInterface {
        public String MuFunction();
    }

    private static class MyClass implements MyInterface {
        @Override
        public String MuFunction() {
            return "MyClass Implements MyInterface!";
        }
    }

}
