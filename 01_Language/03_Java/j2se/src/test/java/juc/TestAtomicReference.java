package juc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class TestAtomicReference {
    @Test
    public void testAtomicReference() {
        User zhangsan = new User("zhangsan", 22);
        User lisi = new User("lisi", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zhangsan);

        boolean b = atomicReference.compareAndSet(zhangsan, lisi);
        System.out.println(b + ": " + atomicReference.get());

        b = atomicReference.compareAndSet(zhangsan, lisi);
        System.out.println(b + ": " + atomicReference.get());
    }
}
