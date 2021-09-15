package com.onevgo.j2se.nio.blocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Server {
    public static void main(String[] args) {
        //1. 获取通道
        ServerSocketChannel ssChannel = null;
        SocketChannel sChannel = null;
        FileChannel outChannel = null;
        try {
            ssChannel = ServerSocketChannel.open();
            outChannel = FileChannel.open(Paths.get("5.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            //2. 绑定连接
            ssChannel.bind(new InetSocketAddress(9898));

            //3. 获取客户端连接的通道
            sChannel = ssChannel.accept();

            //4. 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //5. 接受客户端的数据，并保存到本地
            while (sChannel.read(buf) != -1) {
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6. 关闭通道
            if (sChannel != null) {
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ssChannel != null) {
                try {
                    ssChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
