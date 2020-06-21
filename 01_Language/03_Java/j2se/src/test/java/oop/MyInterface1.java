package oop;

public interface MyInterface1 {
    void myMethod();

    void myMethod1();

    default void myDefaultMethod() {
        System.out.println("MyInterface1.myDefaultMethod");
    }

    static void myStaticMethod() {
        System.out.println("MyInterface1.myStaticMethod");
    }
}
