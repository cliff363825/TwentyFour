package com.onevgo.j2se.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 * <p>
 * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区：
 * ByteBuffer !!
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * <p>
 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 * <p>
 * 二、缓冲区存取数据的两个核心方法：
 * put() : 存入数据到缓冲区中
 * get() : 获取缓冲区中的数据
 * <p>
 * 三、缓冲区中的四个核心属性：
 * capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
 * position : 位置，表示缓冲区中正在操作数据的位置。
 * <p>
 * mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
 * <p>
 * 0 <= mark <= position <= limit <= capacity
 * <p>
 * 四、直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
 * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 */
@Slf4j
public class BufferMain {
    public static void main(String[] args) {
//        testBuffer();
        testDirect();
    }

    private static void testBuffer() {
        String str = "abcde";
        //1. 分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        log.info("=========== allocate ===========");
        log.info("position = {}", buf.position());
        log.info("limit = {}", buf.limit());
        log.info("capacity = {}", buf.capacity());

        //2. 利用 put() 存入数据到缓冲区中
        buf.put(str.getBytes());

        log.info("=========== put ===========");
        log.info("position = {}", buf.position());
        log.info("limit = {}", buf.limit());
        log.info("capacity = {}", buf.capacity());

        //3. 切换读取数据模式
        buf.flip();

        log.info("=========== flip ===========");
        log.info("position = {}", buf.position());
        log.info("limit = {}", buf.limit());
        log.info("capacity = {}", buf.capacity());

        //4. 利用 get() 读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        log.info(new String(dst));

        log.info("=========== get ===========");
        log.info("position = {}", buf.position());
        log.info("limit = {}", buf.limit());
        log.info("capacity = {}", buf.capacity());

        //5. rewind() : 可重复读
        buf.rewind();

        log.info("=========== rewind ===========");
        log.info("position = {}", buf.position());
        log.info("limit = {}", buf.limit());
        log.info("capacity = {}", buf.capacity());

        //6. clear() : 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
        buf.clear();

        log.info("=========== clear ===========");
        log.info("position = {}", buf.position());
        log.info("limit = {}", buf.limit());
        log.info("capacity = {}", buf.capacity());

        log.info("{}", (char) buf.get());
        log.info("{}", (char) buf.get());
        log.info("{}", (char) buf.get());
        log.info("{}", (char) buf.get());
        log.info("{}", (char) buf.get());
        log.info("{}", (char) buf.get());

        buf.clear();

        buf.put(str.getBytes());
        buf.flip();
        buf.get(dst, 0, 2);
        log.info("{}", new String(dst, 0, 2));
        log.info("position = {}", buf.position());

        //mark() : 标记
        buf.mark();

        buf.get(dst, 2, 2);
        log.info("{}", new String(dst, 2, 2));
        log.info("position = {}", buf.position());

        //reset() : 恢复到 mark 的位置
        buf.reset();
        log.info("=========== reset ===========");
        log.info("position = {}", buf.position());
        log.info("limit = {}", buf.limit());
        log.info("capacity = {}", buf.capacity());

        buf.get(dst, 2, 2);
        log.info("{}", new String(dst, 2, 2));
        log.info("position = {}", buf.position());

        //判断缓冲区中是否还有剩余数据
        if (buf.hasRemaining()) {
            //获取缓冲区中可以操作的数量
            log.info("remaining = {}", buf.remaining());
        }
    }

    private static void testDirect() {
        //分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        log.info("isDirect = {}", buf.isDirect());
    }
}
