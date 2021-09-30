package com.onevgo.j2se.nio.nonblocking;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@Slf4j
public class Server {
    public static void main(String[] args) {
        ServerSocketChannel ssChannel = null;
        Selector selector = null;
        try {
            //1. 获取通道
            ssChannel = ServerSocketChannel.open();

            //2. 切换非阻塞模式
            ssChannel.configureBlocking(false);

            //3. 绑定连接
            ssChannel.bind(new InetSocketAddress(9898));

            //4. 获取选择器
            selector = Selector.open();

            //5. 将通道注册到选择器上，并且指定监听接收事件
            // SelectionKey:
            // - OP_ACCEPT: 接收就绪
            // - OP_READ: 读就绪
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);

            //6. 轮询式的获取选择器上已经准备就绪的事件
            while (selector.isOpen() && selector.select() != 0) {
                //7. 获取当前选择器中所有注册的选择键（已就绪的监听事件）
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();

                while (it.hasNext()) {
                    //8. 获取准备就绪的具体事件
                    SelectionKey sk = it.next();

                    //9. 判断具体是什么事件准备就绪
                    if (sk.isAcceptable()) {
                        //10. 若接收就绪，获取客户端连接
                        ServerSocketChannel ssc = (ServerSocketChannel) sk.channel();
                        SocketChannel sChannel = ssc.accept();

                        //11. 切换非阻塞模式
                        sChannel.configureBlocking(false);

                        //12. 将通道注册到选择器上
                        sChannel.register(selector, SelectionKey.OP_READ);
                    } else if (sk.isReadable()) {
                        //13. 获取当前选择器上读就绪状态的通道
                        SocketChannel sChannel = (SocketChannel) sk.channel();

                        //14. 读取数据
                        ByteBuffer buf = ByteBuffer.allocate(1024);

                        // 非阻塞模式下，read读取到的字节数可能为 0，
                        // 不能使用等于 -1 作为判断条件
                        while (sChannel.read(buf) > 0) {
                            buf.flip();
                            log.info("{}", StandardCharsets.UTF_8.decode(buf).toString());
                            buf.clear();
                        }
                    }

                    //15. 取消选择键 SelectionKey
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (selector != null) {
                try {
                    selector.close();
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
