package oop;

import org.junit.Test;

public class TestOOP {
    @Test
    public void testIsAssignableFrom() {
        System.out.println("MyInterface1 是不是 MyObject1 超接口?" + MyInterface1.class.isAssignableFrom(MyObject1.class)); // true
        System.out.println("MyInterface1 是不是 MyObject2 超接口?" + MyInterface1.class.isAssignableFrom(MyObject2.class)); // true

        System.out.println("MyInterface2 是不是 MyObject1 超接口?" + MyInterface2.class.isAssignableFrom(MyObject1.class)); // false
        System.out.println("MyInterface2 是不是 MyObject2 超接口?" + MyInterface2.class.isAssignableFrom(MyObject2.class)); // true

        System.out.println("MyObject1 是不是 MyObject1 超类?" + MyObject1.class.isAssignableFrom(MyObject1.class)); // true
        System.out.println("MyObject1 是不是 MyObject2 超类?" + MyObject1.class.isAssignableFrom(MyObject2.class)); // true
    }

    @Test
    public void testInterface() {
        MyObject1 myObject1 = new MyObject1();
        MyObject2 myObject2 = new MyObject2();
        invokeMyInterface(myObject1);
//        invokeMyInterface(myObject2); // 编译错误：Ambiguous method call
        invokeMyInterface((MyInterface1) myObject2);
        invokeMyInterface((MyInterface2) myObject2);
    }

    private void invokeMyInterface(MyInterface1 myInterface1) {
        myInterface1.myMethod1();
    }

    private void invokeMyInterface(MyInterface2 myInterface2) {
        myInterface2.myMethod2();
    }

    @Test
    public void testInterface1() {
        MyInterface1 myInterface1 = new MyObject1();
        MyInterface2 myInterface2 = new MyObject2();

        myInterface1.myDefaultMethod();
        myInterface2.myDefaultMethod();
    }

    @Test
    public void testInterface2() {
        MyObject3 myObject3 = new MyObject3();
        myObject3.myDefaultMethod();
    }
}
