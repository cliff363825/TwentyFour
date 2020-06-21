package net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTcp2 {
    @Test
    public void testClient() {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8989);

            outputStream = socket.getOutputStream();
            outputStream.write("我是客户端".getBytes());
            outputStream.flush();
            socket.shutdownOutput(); // 执行此方法，显式的告诉服务端发送完毕

            inputStream = socket.getInputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) != -1) {
                String str = new String(b, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(8989);
            Socket s = serverSocket.accept();
            inputStream = s.getInputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) != -1) {
                String str = new String(b, 0, len);
                System.out.println(str);
            }
            outputStream = s.getOutputStream();
            outputStream.write("我已收到你的消息：".getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
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
