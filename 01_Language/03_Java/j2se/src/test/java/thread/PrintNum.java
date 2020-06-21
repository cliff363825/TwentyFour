package thread;

public class PrintNum implements Runnable {
    private int num = 1;

    @Override
    public void run() {
        synchronized (this) {
            while (num <= 100) {
                System.out.println(Thread.currentThread().getName() + ": " + num);
                num++;

                // notify():
                // 唤醒正在此对象监视器上等待的单个线程。
                // 如果有任何线程正在等待这个对象，则选择其中一个线程被唤醒。
                // 这个选择是任意的，由实现来决定。
                // 线程通过调用一个等待方法来等待对象的监视器。
                notify();

                try {
                    // wait():
                    // 导致当前线程等待，直到另一个线程调用此对象的notify()方法或notifyAll()方法。
                    // 换句话说，这个方法的行为与它简单地执行调用wait(0)完全一样。
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            notify();
        }
    }
}
