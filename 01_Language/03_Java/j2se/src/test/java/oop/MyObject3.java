package oop;

public class MyObject3 implements MyInterface1, MyInterface2 {
    @Override
    public void myMethod() {

    }

    @Override
    public void myMethod2() {

    }

    @Override
    public void myMethod1() {

    }

    // 编译错误：inherits unrelated defaults for ...
    // 接口冲突
    @Override
    public void myDefaultMethod() {
        System.out.println("MyObject3.myDefaultMethod");
        MyInterface1.super.myDefaultMethod();
        MyInterface2.super.myDefaultMethod();
    }
}
