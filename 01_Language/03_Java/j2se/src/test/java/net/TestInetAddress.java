package net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
    @Test
    public void test1() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("www.onevgo.com");
        System.out.println("inetAddress = " + inetAddress);
        System.out.println("inetAddress.getHostName() = " + inetAddress.getHostName());
        System.out.println("inetAddress.getHostAddress() = " + inetAddress.getHostAddress());

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost);
    }
}
