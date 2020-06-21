package jvm;

public class TestStrongReference {
    public static void main(String[] args) {
        Object obj1 = new Object(); // 这样定义的默认就是强引用
        Object obj2 = obj1; // obj2引用赋值
        obj1 = null; // 置空
        System.gc();
        System.out.println(obj2);
    }
}
