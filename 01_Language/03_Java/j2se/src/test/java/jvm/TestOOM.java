package jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import sun.misc.VM;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class TestOOM {
    static class MyOOM {

    }

    public static void main(String[] args) {
//        testJavaHeapSpace();
//        testGCOverheadLimitExceeded();
//        testDirectBufferedMemory();
//        testUnableToCreateNativeThread();
        testMetaspace(args);
    }

    // -Xms10m -Xmx10m -XX:+PrintGCDetails
    public static void testJavaHeapSpace() {
        byte[] bytes = new byte[30 * 1024 * 1024]; // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }

    // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
    public static void testGCOverheadLimitExceeded() {
        int i = 0;
        ArrayList<Object> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern()); // Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
            }
        } catch (Throwable e) {
            System.out.println("i=" + i);
            e.printStackTrace();
            throw e;
        }
    }

    // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
    public static void testDirectBufferedMemory() {
        System.out.println("配置的maxDirectMemory=" + (VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024); // Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
    }

    public static void testUnableToCreateNativeThread() {
        for (int i = 1; ; i++) {
            System.out.println("i=" + i);
            // Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i + "").start();
        }
    }

    // -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=10m -XX:+PrintGCDetails
    public static void testMetaspace(String[] args) {
        int i = 0; // 模拟计数多少次以后发生异常

        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(MyOOM.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create(); // java.lang.OutOfMemoryError: Metaspace
            }
        } catch (Throwable e) {
            System.out.println("i=" + i);
            e.printStackTrace();
        }
    }
}
