package com.onevgo.j2se.init;

/**
 * 父类的初始化 <clinit>();
 * 1. 父类的静态类变量 static class variables
 * 2. 父类的静态代码块 static {}
 * 3. 1和2，按代码从上到下顺序执行
 * <p>
 * 父类的实例化方法 <init>();
 * 1. super(); 调用父类的 <init>() 方法
 * 2. 父类的非静态实例变量 non-static instance variables
 * 3. 父类的非静态代码块 non-static {}
 * 4. 2和3，按代码从上到下顺序执行
 * 4. 父类的构造器最后执行
 * <p>
 * 非静态方法前面其实有一个默认的对象this
 * this在构造器（或<init>）它表示的是正在创建的对象，因为这里是在创建 Son 对象，
 * 所以，initName() initAge() initSalary() 执行的是子类重写的代码
 */
public class Father {
    private static String english = inEnglish();

    static {
        System.out.print("(1)"); // 2
    }

    private static String chinese = inChinese();

    private String name = initName();
    private int age = initAge();

    {
        System.out.print("(2)"); // 9
    }

    private Integer salary = initSalary();

    public Father() {
        System.out.print("(3)"); // 11
    }

    public static String inEnglish() {
        System.out.print("(4)"); // 1
        return "father";
    }

    public static String inChinese() {
        System.out.print("(5)"); // 3
        return "父亲";
    }

    public String initName() {
        System.out.print("(6)");
        return "张三";
    }

    public int initAge() {
        System.out.print("(7)");
        return 30;
    }

    public Integer initSalary() {
        System.out.print("(8)");
        return 3000;
    }
}
