package com.onevgo.functions;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Gethostbyname {
    public static String gethostbyname(String hostname) {
        try {
            return InetAddress.getByName(hostname).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostname;
    }

    public static void main(String[] args) {
        System.out.println(gethostbyname("www.onevgo.com"));
        System.out.println(gethostbyname("ss.onevgo.com"));
    }
}
