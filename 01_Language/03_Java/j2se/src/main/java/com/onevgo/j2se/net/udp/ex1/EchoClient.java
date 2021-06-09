package com.onevgo.j2se.net.udp.ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte[] b = "你好，我是要发送的数据".getBytes();
            // 创建一个数据报：每一个数据报不能大于64K，都记录着数据信息，发送端的IP、端口号，以及要发送到的接收端的IP、端口号
            DatagramPacket datagramPacket = new DatagramPacket(b, 0, b.length,
                    InetAddress.getByName("127.0.0.1"), 9090);

            datagramSocket.send(datagramPacket);
        }
    }
}
