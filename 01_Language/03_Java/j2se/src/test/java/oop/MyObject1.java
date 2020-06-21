package oop;

public class MyObject1 implements MyInterface1 {
    @Override
    public void myMethod() {
        System.out.println("MyObject1.myMethod");
        myStaticMethod();
    }

    @Override
    public void myMethod1() {
        System.out.println("MyObject1.myMethod1");
    }

    @Override
    public void myDefaultMethod() {
        System.out.println("MyObject1.myDefaultMethod");
    }

    public static void myStaticMethod() {
        System.out.println("MyObject1.myStaticMethod");
    }
}
