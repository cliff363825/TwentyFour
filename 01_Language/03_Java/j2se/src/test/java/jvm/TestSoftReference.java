package jvm;

import java.lang.ref.SoftReference;

public class TestSoftReference {
    // 内存够用的时候就保留，不够用就回收
    public static void softReferenceMemoryEnough() {
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);

        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;
        System.gc();

        System.out.println(obj1);
        System.out.println(softReference.get());
    }

    // JVM配置，故意产生大对象并配置小内存，让它内存不够用了导致OOM，看软引用的回收情况
    // -Xms5m -Xmx5m -XX:+PrintGCDetails
    public static void softReferenceMemoryNotEnough() {
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);

        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(obj1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
//        softReferenceMemoryEnough();
        softReferenceMemoryNotEnough();
    }
}
