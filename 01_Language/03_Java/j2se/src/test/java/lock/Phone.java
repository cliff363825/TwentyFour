package lock;

import java.util.concurrent.locks.ReentrantLock;

public class Phone implements Runnable {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + ": invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + ": invoked sendEmail()");
    }

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": invoked get()");
            set();
        } finally {
            lock.unlock();
            lock.unlock(); // lock 和 unlock 要成对出现
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": invoked set()");
        } finally {
            lock.unlock();
        }
    }
}
