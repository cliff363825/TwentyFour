package com.onevgo.j2se.init;

public class InitMain {
    public static void main(String[] args) {
        // 类的初始化过程 <clinit>()
        // 1. 一个类要创建实例需要先加载并初始化该类
        // 2. 一个子类要初始化需要先初始化父类
        // 3. 一个类初始化就是执行 <clinit>() 方法
        //  <clinit>() 方法由静态类变量显示赋值代码(static class variables)和静态代码块(static {})组成。
        //  类变量显示赋值代码和静态代码块代码从上到下顺序执行。
        //  <clinit>() 方法只执行一次

        // 实例的初始化过程 <init>()
        // 1. 实例初始化就是执行 <init>() 方法
        //  <init> 方法可以重载有多个，有几个构造器就有几个 <init> 方法
        //  <init> 方法由非静态实例变量显示赋值代码(non-static instance variables)和非静态代码块(non-static {})、对应构造代码(constructor)组成
        //  非静态实例变量显示赋值代码和非静态代码块代码从上到下顺序执行，而对应构造器的代码最后执行
        //  每次创建实例对象，调用对应构造器，执行的就是对应的 <init> 方法
        //  <init> 方法的首行是 super() 或 super(实参列表)，即对应父类的 <init> 方法

        // 方法的重写<override>
        // 1. 哪些方法不可以被重写
        //  final 方法
        //  静态方法
        //  private等子类不可见方法
        // 2. 对象的多态性
        //  1. 子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的代码
        //  2. 非静态方法默认的调用对象是this
        //  3. this对象在构造器或者说 <init> 方法中就是正在创建的对象
        Son son = new Son(); // (4)(1)(5)(12)(9)(13)(14)(15)(2)(16)(3)(14)(15)(10)(16)(11)
        System.out.println();
        Son son2 = new Son(); // (14)(15)(2)(16)(3)(14)(15)(10)(16)(11)
    }
}
