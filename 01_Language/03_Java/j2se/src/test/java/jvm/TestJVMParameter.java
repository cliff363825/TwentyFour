package jvm;

public class TestJVMParameter {
    public static void main(String[] args) {
        // -XX:+PrintCommandLineFlags: 这个标志告诉JVM打印用户或JVM在启动时设置的XX标志的名称和值。
        // 本机默认启动参数：
        // -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:MetaspaceSize=21807104  -XX:+PrintCommandLineFlags -XX:-PrintGCDetails -XX:ThreadStackSize=1024 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC

        // 自定义启动参数：
        // -Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
        // -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=4294967296 -XX:MetaspaceSize=536870912 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:ThreadStackSize=1024 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseSerialGC
        long totalMemory = Runtime.getRuntime().totalMemory(); // 返回java虚拟机中的内存总量
        long maxMemory = Runtime.getRuntime().maxMemory(); // 返回java虚拟机试图使用的最大内存量

        System.out.println("totalMemory(-Xms)=" + totalMemory + "B, " + (totalMemory * 1.0 / 1024 / 1024) + "MB");
        System.out.println("maxMemory(-Xmx)=" + maxMemory + "B, " + (maxMemory * 1.0 / 1024 / 1024) + "MB");

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // jps -l
        // jinfo -flag <参数名> <pid>
        // jinfo -flags <pid>
    }
}
