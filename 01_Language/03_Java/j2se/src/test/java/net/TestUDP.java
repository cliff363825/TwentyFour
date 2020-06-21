package net;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

public class TestUDP {
    @Test
    public void send() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();

            byte[] b = "你好，我是要发送的数据".getBytes();
            // 创建一个数据报：每一个数据报不能大于64K，都记录着数据信息，发送端的IP、端口号，以及要发送到的接收端的IP、端口号
            DatagramPacket datagramPacket = new DatagramPacket(b, 0, b.length,
                    InetAddress.getByName("127.0.0.1"), 9090);

            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    @Test
    public void receive() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(9090);

            byte[] b = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(b, 0, b.length);
            datagramSocket.receive(datagramPacket);

            String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}
