package com.onevgo.functions;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetNtop {
    public static String inetNtop(byte[] inAddr) {
        try {
            return InetAddress.getByAddress(inAddr).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(inetNtop(new byte[]{127, 0, 0, 1}));
        byte[] ipv6 = new byte[16];
        ipv6[15] = 1;
        System.out.println(inetNtop(ipv6));
    }
}
