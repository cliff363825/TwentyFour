package com.onevgo.j2se.net.tcp.ex3;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class UploadServer {
    public static void main(String[] args) throws IOException {
        try (final ServerSocket ss = new ServerSocket(9898)) {
            try (final Socket s = ss.accept()) {
                final OutputStream outputStream = s.getOutputStream();

                try (final InputStream inputStream = s.getInputStream();
                     final PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {

                    try (final FileOutputStream fileOutputStream = new FileOutputStream(new File("upload.jpg"))) {
                        final byte[] b = new byte[8192];
                        int len;
                        while ((len = inputStream.read(b, 0, 8192)) != -1) {
                            fileOutputStream.write(b, 0, len);
                            fileOutputStream.flush();
                        }
                    }

                    out.println("你发送的图片我已接收成功");
                }
            }
        }
    }
}
