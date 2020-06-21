package juc;

public class TestDeadLock {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "AAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "BBB").start();
        // jps -l: 定位进程号
        // 54385 juc.TestDeadLock

        // jstack 54385: 找到死锁查看
    }
}
