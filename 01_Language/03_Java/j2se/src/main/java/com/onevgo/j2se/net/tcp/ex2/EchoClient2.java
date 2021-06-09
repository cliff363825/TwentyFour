package com.onevgo.j2se.net.tcp.ex2;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class EchoClient2 {
    public static void main(String[] args) throws IOException {
        try (final Socket s = new Socket()) {
            s.connect(new InetSocketAddress("127.0.0.1", 8989), 3000);
            s.setSoTimeout(2000);

            final InputStream inputStream = s.getInputStream();
            final OutputStream outputStream = s.getOutputStream();

            try (final Scanner scanner = new Scanner(inputStream, "UTF-8");
                 final PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {

                out.println("我是客户端，请多关照");
                out.flush();

                // 关闭Socket的输入流或输出流将关闭另一个流和Socket。
                // outputStream.close();

                // 执行此方法，显式的告诉服务端发送完毕
                s.shutdownOutput();

                while (scanner.hasNextLine()) {
                    final String line = scanner.nextLine();
                    log.info("服务端返回信息={}", line);
                }
            }
        }
    }
}
