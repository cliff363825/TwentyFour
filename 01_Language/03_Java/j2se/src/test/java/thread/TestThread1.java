package thread;

public class TestThread1 {
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.setName("A");
        myThread.start();
//        myThread.setPriority(Thread.MAX_PRIORITY);

//        Thread.currentThread().setName("主线程");
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);

//            if (i % 10 == 0) {
//                // yield()：线程让步
//                // 暂停当前正在执行的线程，把执行机会让给优先级相同或更高的线程
//                // 若队列中没有同优先级的线程，忽略此方法
//                Thread.yield();
//            }

//            if (i == 20) {
//                try {
//                    // join():
//                    // 当某个程序执行流中调用其他线程的 join() 方法时，调用线程将被阻塞，直到 join() 方法加入的 join 线程执行完为止
//                    // 低优先级的线程也可以获得执行
//                    myThread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
