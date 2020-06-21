package thread;

import java.util.concurrent.TimeUnit;

public class TestThreadLocal {
    private static MyThreadLocal globalVar = new MyThreadLocal();
    private static MyObject childObject;

    static class MyThreadLocal extends ThreadLocal<MyObject> {
        public int num = 0;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyThreadLocal.finalize");
        }
    }

    static class MyObject {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyObject.finalize");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                childObject = new MyObject();
                globalVar.set(childObject);
                System.out.println(Thread.currentThread().getName() + ": " + globalVar.get());

                globalVar.num++;
                System.out.println(Thread.currentThread().getName() + ": " + globalVar.num);

                globalVar.remove();
            }
        }, "A").start();

        globalVar.set(new MyObject());
        System.out.println(Thread.currentThread().getName() + ": " + globalVar.get());

        TimeUnit.SECONDS.sleep(1);

        System.out.println(Thread.currentThread().getName() + ": " + (globalVar.get() == childObject)); // false
        System.out.println(Thread.currentThread().getName() + ": " + globalVar.num); // 1
        System.out.println("System.gc() ... 1");
        System.gc();
        globalVar.remove();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("System.gc() ... 2");
        System.gc();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("over");

        /*
        thread.TestThreadLocal$MyObject@1bcfca5d
        false
        1
        System.gc() ... 1
        System.gc() ... 2
        MyObject.finalize
        over
         */
    }
}
