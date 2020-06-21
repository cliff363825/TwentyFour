package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("MyCallable.call");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}
