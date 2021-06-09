package com.onevgo.j2se.net;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
//        testInetAddress();
//        testSocket();
//        testSocketTimeout();
//        testUrl();
        testUrlConnection();
    }

    private static void testSocketTimeout() throws IOException {
        try (final Socket s = new Socket()) {
            SocketAddress address = new InetSocketAddress("time-a.nist.gov", 13);
            // 建立连接超时时间
            s.connect(address, 3000);
            // 读写数据超时时间
            s.setSoTimeout(2000);

            try (final Scanner scanner = new Scanner(s.getInputStream(), "UTF-8")) {
                while (scanner.hasNextLine()) {
                    final String line = scanner.nextLine();
                    log.info("len = {} line = {}", line.length(), line);
                }
            }
        }
    }

    private static void testSocket() throws IOException {
        try (Socket s = new Socket("time-a.nist.gov", 13);
             Scanner in = new Scanner(s.getInputStream(), "UTF-8")) {
            while (in.hasNextLine()) {
                final String line = in.nextLine();
                log.info("len = {}, line = {}", line.length(), line);
            }
        }
    }

    private static void testInetAddress() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("www.onevgo.com");
        log.info("inetAddress = {}", inetAddress);

        InetAddress localHost = InetAddress.getLocalHost();
        log.info("localHost = {}", localHost);
    }

    private static void testUrl() throws MalformedURLException {
        URL url = new URL("https://www.onevgo.com");
        log.info("url={}", url);

        // 获取该URL的协议名
        log.info("url.getProtocol()={}", url.getProtocol());

        // 获取该URL的主机名
        log.info("url.getHost()={}", url.getHost());

        // 获取该URL的端口名
        log.info("url.getPort()={}", url.getPort());

        // 获取该URL的文件路径
        log.info("url.getPath()={}", url.getPath());

        // 获取该URL的文件名
        log.info("url.getFile()={}", url.getFile());

        // 获取该URL的查询名
        log.info("url.getQuery()={}", url.getQuery());
    }

    private static void testUrlConnection() throws IOException {
        final URL url = new URL("https://www.onevgo.com");
        final URLConnection connection = url.openConnection();
        connection.connect();

        File file = new File("url.html");

        try (final Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8");
             final PrintWriter writer = new PrintWriter(new FileWriter(file), true)) {
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                writer.println(line);
            }
        }
    }
}
