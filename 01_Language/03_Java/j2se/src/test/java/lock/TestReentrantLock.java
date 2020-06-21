package lock;

public class TestReentrantLock {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> phone.sendSMS(), "t1").start();
        new Thread(() -> phone.sendSMS(), "t2").start();
        //t1: invoked sendSMS()         t1线程在外层方法获取锁的时候
        //t1: invoked sendEmail()       t1在进入内层方法会自动获取锁
        //t2: invoked sendSMS()
        //t2: invoked sendEmail()

        new Thread(phone, "t3").start();
        new Thread(phone, "t4").start();
        //t3: invoked get()
        //t3: invoked set()
        //t4: invoked get()
        //t4: invoked set()
    }
}
