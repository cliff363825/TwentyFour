package juc;

import java.util.concurrent.*;

public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyCallable());

        new Thread(futureTask).start(); // MyCallable.call
        new Thread(futureTask1).start();

        int result1 = 100;
        int result2 = futureTask.get(); // 要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞，值得计算完成

        System.out.println(result1 + result2);
    }
}
