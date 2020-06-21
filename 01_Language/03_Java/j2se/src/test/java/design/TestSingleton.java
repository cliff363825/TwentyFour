package design;

import design.singleton.*;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleton {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton instance = Singleton.getInstance();
                    Singleton1 instance1 = Singleton1.INSTANCE;
                    Singleton2 instance2 = Singleton2.INSTANCE;
                    SingletonEnum instanceEnum = SingletonEnum.INSTANCE;
                    SingletonInner instanceInner = SingletonInner.getInstance();
                    SingletonLazy instanceLazy = SingletonLazy.getInstance();
                    SingletonLazy2 instanceLazy2 = SingletonLazy2.getInstance();

                    //0: design.singleton.Singleton         ok
                    //0: design.singleton.Singleton1        ok
                    //0: design.singleton.Singleton2        ok
                    //0: design.singleton.SingletonEnum     ok
                    //8: design.singleton.SingletonInner    ok
                    //8: design.singleton.SingletonLazy     x
                    //2: design.singleton.SingletonLazy     x
                    //9: design.singleton.SingletonLazy     x
                    //8: design.singleton.SingletonLazy2    ok
                }
            }, i + "").start();
        }
    }
}
