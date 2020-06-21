package net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.*;

public class TestTcp1 {
    @Test
    public void testClient() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                Socket socket = null;
                OutputStream outputStream = null;
                try {
                    // 1. 创建一个Socket对象，通过构造器指明服务端的IP地址，以及其接收程序的端口号
                    socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
                    // 2. getOutputStream()：发送数据，方法返回OutputStream的对象
                    outputStream = socket.getOutputStream();
                    // 3. 具体的输出过程
                    outputStream.write("我是客户端，请多关照".getBytes(Charset.forName("UTF-8")));
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // 4. 关闭相应的流和Socket对象
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testServer() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 3,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        // 1. 创建一个ServerSocket对象，通过构造器指明自身的端口号
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9090);

            for (int errCount = 0; errCount < 10; ) {
                Socket socket = null;
                try {
                    // 2. 调用其accept()方法，返回一个Socket对象
                    socket = serverSocket.accept();
                    errCount = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                    errCount++;
                    continue;
                }

                final Socket mySocket = socket;
                try {
                    threadPool.execute(() -> {
                        InputStream inputStream = null;
                        try {
                            // 3. 调用Socket对象的getInputStream()获取一个从客户端发送过来的输入流
                            inputStream = mySocket.getInputStream();
                            // 4. 对获取的输入流进行的操作
                            byte[] b = new byte[1024];
                            int len;
                            while ((len = inputStream.read(b)) != -1) {
                                String str = new String(b, 0, len);
                                System.out.println(Thread.currentThread().getName() + ": " + str);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            // 5. 关闭相应的流以及Socket。
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (mySocket != null) {
                                try {
                                    mySocket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } catch (RejectedExecutionException e) {
                    e.printStackTrace();
                    try {
                        mySocket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
            try {
                threadPool.awaitTermination(10L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
