package com.onevgo.functions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Fsockopen {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("www.onevgo.com", 80);
            OutputStream outputStream = socket.getOutputStream();
            String content = "GET / HTTP/1.1\r\n" +
                    "Host: www.onevgo.com\r\n" +
                    "Connection: Close\r\n\r\n";
            byte[] b = content.getBytes();
            outputStream.write(b);
            outputStream.flush();
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            byte[] b1 = new byte[4096];
            int i = inputStream.read(b1);
            System.out.println(new String(b1, 0, i));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
