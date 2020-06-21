package jvm;

public class TestGCRoot {
    private byte[] byteArray = new byte[100 * 1024 * 1024];

    private static class MyGCRoot {

    }

    // 2. 方法区中的类静态属性引用的对象
    private static MyGCRoot myGcRoot = new MyGCRoot();
    // 3. 方法区中常量引用的对象
    private static final MyGCRoot myGcRoot2 = new MyGCRoot();
    // 4. 本地方法栈中JNI（即一般说的Native方法）中引用的对象

    public static void m1() {
        // 1. 虚拟机栈（栈帧中的本地变量表）中引用的对象
        TestGCRoot testGcRoot = new TestGCRoot();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
