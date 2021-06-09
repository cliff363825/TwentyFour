package com.onevgo.j2se.net.tcp.ex1;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class EchoClient {
    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress("127.0.0.1", 9090), 3000);
            s.setSoTimeout(2000);

            final InputStream inputStream = s.getInputStream();
            final OutputStream outputStream = s.getOutputStream();

            try (final Scanner scanner = new Scanner(inputStream);
                 final PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {

                out.println("我是客户端，请多关照");
                out.println("BYE");

                while (scanner.hasNextLine()) {
                    log.info("服务端返回信息={}", scanner.nextLine());
                }
            }
        }
    }
}
