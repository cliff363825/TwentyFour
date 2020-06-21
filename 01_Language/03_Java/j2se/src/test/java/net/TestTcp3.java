package net;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTcp3 {
    @Test
    public void testClient() {
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            // 1. 创建Socket对象
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9898);
            // 2. 从本地获取一个文件发送给服务端
            outputStream = socket.getOutputStream();
            fileInputStream = new FileInputStream(new File("travel.jpg"));
            byte[] b = new byte[1024];
            int len;
            while ((len = fileInputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
                outputStream.flush();
            }
            socket.shutdownOutput();
            // 3. 接收来自于服务端的信息
            inputStream = socket.getInputStream();
            byte[] b1 = new byte[1024];
            int len1;
            while ((len1 = inputStream.read(b1)) != -1) {
                String str = new String(b1, 0, len1);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭相应的流和Socket对象
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
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
    }

    @Test
    public void testServer() {
        ServerSocket serverSocket = null;
        OutputStream outputStream = null;
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        Socket socket = null;
        try {
            // 1. 创建一个ServerSocket对象
            serverSocket = new ServerSocket(9898);
            // 2. 调用其accept()方法，返回一个Socket对象
            socket = serverSocket.accept();
            // 3.  将从客户端发送来的信息保存到本地
            inputStream = socket.getInputStream();
            fileOutputStream = new FileOutputStream(new File("travel-server.jpg"));
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
                fileOutputStream.flush();
            }
            // 4. 发送 接收成功 的信息反馈给客户端
            outputStream = socket.getOutputStream();
            outputStream.write("你发送的图片我已接收成功".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭相应的流和Socket及ServeSocket的对象
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
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
