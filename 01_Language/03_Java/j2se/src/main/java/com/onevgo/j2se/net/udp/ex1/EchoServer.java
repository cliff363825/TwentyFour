package com.onevgo.j2se.net.udp.ex1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Slf4j
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket datagramSocket = new DatagramSocket(9090)) {
            byte[] b = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(b, 0, b.length);
            datagramSocket.receive(datagramPacket);

            String s = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            log.info("服务端接收信息={}", s);
        }
    }
}
