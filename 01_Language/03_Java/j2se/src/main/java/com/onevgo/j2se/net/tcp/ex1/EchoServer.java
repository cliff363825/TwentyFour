package com.onevgo.j2se.net.tcp.ex1;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (final ServerSocket ss = new ServerSocket(9090)) {
            try (final Socket s = ss.accept()) {
                final InputStream inputStream = s.getInputStream();
                final OutputStream outputStream = s.getOutputStream();

                try (final Scanner scanner = new Scanner(inputStream);
                     final PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {

                    out.println("Hello! Enter BYE to exit.");

                    while (scanner.hasNextLine()) {
                        final String line = scanner.nextLine();

                        log.info("服务端接收信息={}", line);
                        out.println("Echo: " + line);

                        if ("BYE".equals(line.trim())) {
                            break;
                        }
                    }
                }
            }
        }
    }
}
