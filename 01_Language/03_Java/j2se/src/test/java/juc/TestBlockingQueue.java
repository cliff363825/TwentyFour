package juc;

import org.junit.Test;

import java.util.concurrent.*;

public class TestBlockingQueue {
    // add(): 如果可以在不超过队列容量的情况下立即插入指定的元素，成功后返回true，如果队列已满则抛出IllegalStateException。
    // remove(): 检索并删除此队列的头。此方法与 poll 的唯一不同之处在于，它在队列为空时抛出异常。此实现将返回轮询结果，除非队列为空。
    // element(): 检索但不删除此队列的头。此方法与 peek 的唯一不同之处在于，它在此队列为空时抛出异常。除非队列为空，否则此实现将返回peek的结果。
    @Test
    public void testAddRemove() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("blockingQueue.add(a)=" + blockingQueue.add("a"));
        System.out.println("blockingQueue.add(b)=" + blockingQueue.add("b"));
        System.out.println("blockingQueue.add(c)=" + blockingQueue.add("c"));
        // System.out.println("blockingQueue.add(x)=" + blockingQueue.add("x")); // java.lang.IllegalStateException: Queue full

        System.out.println("blockingQueue.element()=" + blockingQueue.element());

        System.out.println("blockingQueue.remove()=" + blockingQueue.remove());
        System.out.println("blockingQueue.remove()=" + blockingQueue.remove());
        System.out.println("blockingQueue.remove()=" + blockingQueue.remove());
        // System.out.println("blockingQueue.remove()=" + blockingQueue.remove()); // java.util.NoSuchElementException

        // System.out.println("blockingQueue.element()=" + blockingQueue.element()); // java.util.NoSuchElementException
    }

    // offer(): 如果可以在不违反容量限制的情况下立即将指定的元素插入到此队列中，如果成功则返回true，如果当前没有可用空间则返回false。在使用容量受限的队列时，此方法通常比 add 方法更好，因为 add 方法在插入元素失败时只能通过抛出异常。
    // poll(): 检索并删除此队列的头，如果此队列为空，则返回null。
    // peek(): 检索但不删除此队列的头，或在此队列为空时返回null。
    @Test
    public void testOfferPoll() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("blockingQueue.offer(a)=" + blockingQueue.offer("a"));
        System.out.println("blockingQueue.offer(b)=" + blockingQueue.offer("b"));
        System.out.println("blockingQueue.offer(c)=" + blockingQueue.offer("c"));
        System.out.println("blockingQueue.offer(d)=" + blockingQueue.offer("x")); // false

        System.out.println("blockingQueue.peek()=" + blockingQueue.peek()); // a

        System.out.println("blockingQueue.poll()=" + blockingQueue.poll());
        System.out.println("blockingQueue.poll()=" + blockingQueue.poll());
        System.out.println("blockingQueue.poll()=" + blockingQueue.poll());
        System.out.println("blockingQueue.poll()=" + blockingQueue.poll()); // null

        System.out.println("blockingQueue.peek()=" + blockingQueue.peek()); // null
    }

    // put(): 将指定的元素插入此队列，在必要时等待空间可用。
    // take(): 检索并删除此队列的头，如有必要则等待，直到某个元素可用为止。
    @Test
    public void testPutTake() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("---------------");
        // blockingQueue.put("x"); // blocking

        System.out.println("blockingQueue.peek()=" + blockingQueue.peek()); // a

        System.out.println("blockingQueue.take()=" + blockingQueue.take());
        System.out.println("blockingQueue.take()=" + blockingQueue.take());
        System.out.println("blockingQueue.take()=" + blockingQueue.take());
        // System.out.println(blockingQueue.take()); // blocking

        System.out.println("blockingQueue.peek()=" + blockingQueue.peek()); // null
    }

    // offer(E e, long timeout, TimeUnit unit): 将指定的元素插入此队列，如果需要空间可用，则等待指定的等待时间。
    // poll(long timeout, TimeUnit unit): 检索并删除此队列的头，如果元素变得可用，则需要等待指定的等待时间。
    @Test
    public void testOfferPollTimeout() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println("blockingQueue.offer(a,2s)=" + blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println("blockingQueue.offer(b,2s)=" + blockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println("blockingQueue.offer(c,2s)=" + blockingQueue.offer("c", 2, TimeUnit.SECONDS));
        System.out.println("blockingQueue.offer(x,2s)=" + blockingQueue.offer("x", 2, TimeUnit.SECONDS)); // false

        System.out.println("blockingQueue.poll(2s)=" + blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println("blockingQueue.poll(2s)=" + blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println("blockingQueue.poll(2s)=" + blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println("blockingQueue.poll(2s)=" + blockingQueue.poll(2, TimeUnit.SECONDS)); // null
    }

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + ": put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + ": put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + ": put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": take " + blockingQueue.take());

                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": take " + blockingQueue.take());

                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": take " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
