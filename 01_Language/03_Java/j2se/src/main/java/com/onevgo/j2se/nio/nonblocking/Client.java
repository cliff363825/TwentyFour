package com.onevgo.j2se.nio.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        SocketChannel sChannel = null;
        try {
            //1. 获取通道
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            //2. 切换非阻塞模式
            sChannel.configureBlocking(false);

            //3. 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //4. 发送数据到服务端
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String s = scanner.next();
                buf.put(s.getBytes(StandardCharsets.UTF_8));
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭通道
            if (sChannel != null) {
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
