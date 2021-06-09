package com.onevgo.j2se.net.tcp.ex3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class UploadClient {
    public static void main(String[] args) throws IOException {
        try (final Socket s = new Socket()) {
            s.connect(new InetSocketAddress("127.0.0.1", 9898), 3000);
            s.setSoTimeout(2000);

            final InputStream inputStream = s.getInputStream();

            try (final Scanner scanner = new Scanner(inputStream);
                 final OutputStream outputStream = s.getOutputStream()) {

                try (final InputStream resourceInputStream = UploadClient.class.getResourceAsStream("/icon.jpg")) {
                    final byte[] b = new byte[8192];
                    int len;
                    while ((len = resourceInputStream.read(b, 0, 8192)) != -1) {
                        outputStream.write(b, 0, len);
                        outputStream.flush();
                    }
                }

                // 告诉服务端 已经发送完毕
                s.shutdownOutput();

                while (scanner.hasNextLine()) {
                    log.info("服务端返回信息={}", scanner.nextLine());
                }
            }
        }
    }
}
