package oop;

public class MyObject2 extends MyObject1 implements MyInterface2 {
    @Override
    public void myMethod() {
        System.out.println("MyObject2.myMethod");
        super.myMethod();
    }

    @Override
    public void myMethod2() {
        System.out.println("MyObject2.myMethod2");
    }

    public static void myStaticMethod() {
        System.out.println("MyObject2.myStaticMethod");
    }
}
