package com.onevgo.j2se.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BlockingQueueMain {
    public static void main(String[] args) {
//        testAddAndRemove();
//        testOfferAndPoll();
//        testPutAndTake();
//        testOfferAndPollTimeout();
        testSynchronousQueue();
    }

    // add(): 如果可以在不超过队列容量的情况下立即插入指定的元素，成功后返回true，如果队列已满则抛出IllegalStateException。
    // remove(): 检索并删除此队列的头。此方法与 poll 的唯一不同之处在于，它在队列为空时抛出异常。此实现将返回轮询结果，除非队列为空。
    // element(): 检索但不删除此队列的头。此方法与 peek 的唯一不同之处在于，它在此队列为空时抛出异常。除非队列为空，否则此实现将返回peek的结果。
    private static void testAddAndRemove() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        log.info("add(a)={}", blockingQueue.add("a"));
        log.info("add(b)={}", blockingQueue.add("b"));
        log.info("add(c)={}", blockingQueue.add("c"));
//        log.info("add(d)={}", blockingQueue.add("d")); // java.lang.IllegalStateException: Queue full

        log.info("element={}", blockingQueue.element());

        log.info("remove()={}", blockingQueue.remove());
        log.info("remove()={}", blockingQueue.remove());
        log.info("remove()={}", blockingQueue.remove());
//        log.info("remove()={}", blockingQueue.remove()); // java.util.NoSuchElementException

//        log.info("element={}", blockingQueue.element()); // java.util.NoSuchElementException
    }

    // offer(): 如果可以在不违反容量限制的情况下立即将指定的元素插入到此队列中，如果成功则返回true，如果当前没有可用空间则返回false。在使用容量受限的队列时，此方法通常比 add 方法更好，因为 add 方法在插入元素失败时只能通过抛出异常。
    // poll(): 检索并删除此队列的头，如果此队列为空，则返回null。
    // peek(): 检索但不删除此队列的头，或在此队列为空时返回null。
    private static void testOfferAndPoll() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        log.info("offer(a)={}", blockingQueue.offer("a"));
        log.info("offer(b)={}", blockingQueue.offer("b"));
        log.info("offer(c)={}", blockingQueue.offer("c"));
        log.info("offer(d)={}", blockingQueue.offer("d")); // false

        log.info("peek()={}", blockingQueue.peek());

        log.info("poll()={}", blockingQueue.poll());
        log.info("poll()={}", blockingQueue.poll());
        log.info("poll()={}", blockingQueue.poll());
        log.info("poll()={}", blockingQueue.poll()); // null

        log.info("peek()={}", blockingQueue.peek()); // null
    }

    // put(): 将指定的元素插入此队列，在必要时等待空间可用。
    // take(): 检索并删除此队列的头，如有必要则等待，直到某个元素可用为止。
    private static void testPutAndTake() {
        try {
            BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");

            log.info("{}", blockingQueue);
//            blockingQueue.put("x"); // blocking
            log.info("peek={}", blockingQueue.peek()); // a

            log.info("take={}", blockingQueue.take());
            log.info("take={}", blockingQueue.take());
            log.info("take={}", blockingQueue.take());
//            log.info("take={}", blockingQueue.take()); // blocking

            log.info("peek={}", blockingQueue.peek()); // null
        } catch (InterruptedException e) {
            log.error("error=", e);
        }
    }

    // offer(E e, long timeout, TimeUnit unit): 将指定的元素插入此队列，如果需要空间可用，则等待指定的等待时间。
    // poll(long timeout, TimeUnit unit): 检索并删除此队列的头，如果元素变得可用，则需要等待指定的等待时间。
    private static void testOfferAndPollTimeout() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        try {
            log.info("offer(a,2s)={}", blockingQueue.offer("a", 2, TimeUnit.SECONDS));
            log.info("offer(b,2s)={}", blockingQueue.offer("b", 2, TimeUnit.SECONDS));
            log.info("offer(c,2s)={}", blockingQueue.offer("c", 2, TimeUnit.SECONDS));
            log.info("offer(x,2s)={}", blockingQueue.offer("x", 2, TimeUnit.SECONDS)); // false

            log.info("poll(2s)={}", blockingQueue.poll(2, TimeUnit.SECONDS));
            log.info("poll(2s)={}", blockingQueue.poll(2, TimeUnit.SECONDS));
            log.info("poll(2s)={}", blockingQueue.poll(2, TimeUnit.SECONDS));
            log.info("poll(2s)={}", blockingQueue.poll(2, TimeUnit.SECONDS)); // null
        } catch (InterruptedException e) {
            log.error("error=", e);
        }
    }

    private static void testSynchronousQueue() {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                blockingQueue.put("1");
                log.info("put 1");

                blockingQueue.put("2");
                log.info("put 2");

                blockingQueue.put("3");
                log.info("put 3");
            } catch (InterruptedException e) {
                log.error("error=", e);
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                log.info("take()={}", blockingQueue.take());

                TimeUnit.SECONDS.sleep(1);
                log.info("take()={}", blockingQueue.take());

                TimeUnit.SECONDS.sleep(1);
                log.info("take()={}", blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
