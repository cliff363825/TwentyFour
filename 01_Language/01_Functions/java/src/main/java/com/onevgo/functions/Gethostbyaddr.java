package com.onevgo.functions;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Gethostbyaddr {
    public static String gethostbyaddr(String addr) {
        try {
            return InetAddress.getByName(addr).getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return addr;
    }

    public static void main(String[] args) {
        System.out.println(gethostbyaddr("193.112.23.137"));
    }
}
