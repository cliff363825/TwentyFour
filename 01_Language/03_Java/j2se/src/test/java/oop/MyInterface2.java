package oop;

public interface MyInterface2 {
    void myMethod();

    void myMethod2();

    default void myDefaultMethod() {
        System.out.println("MyInterface2.myDefaultMethod");
    }
}
