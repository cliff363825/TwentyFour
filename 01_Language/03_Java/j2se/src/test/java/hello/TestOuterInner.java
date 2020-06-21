package hello;

import org.junit.Test;

public class TestOuterInner {
    @Test
    public void test1() {
        MyOuter myOuter = new MyOuter();
        System.out.println("----------");
        MyOuter.MyInner myInner = myOuter.new MyInner();
        System.out.println("----------");
        MyOuter.MyStaticInner myStaticInner = new MyOuter.MyStaticInner();
        System.out.println("----------");
        // 1. 外部类的非静态内部类(non-static inner class)：先有外部类实例，才有内部类实例。
        // 2. 外部类的静态内部类(static inner class)：不会随着外部类的初始化而初始化，不需要创建外部类实例。
    }
}
