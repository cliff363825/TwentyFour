package com.onevgo.functions;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ip2long {
    public static long ip2long(String ipAddress) {
        long result = 0;
        try {
            byte[] address = InetAddress.getByName(ipAddress).getAddress();
            for (int i = 0; i < address.length; i++) {
                result = (result << 8) | (address[i] & 0xFF);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(ip2long(Gethostbyname.gethostbyname("www.onevgo.com")));
        System.out.println(ip2long("193.112.23.137"));
        System.out.println(ip2long("999.999.999.999"));
    }
}
