package com.onevgo.functions;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Gethostbynamel {
    public static List<String> gethostbynamel(String hostname) {
        List<String> res = new ArrayList<>();
        try {
            InetAddress[] inetAddresses = InetAddress.getAllByName(hostname);
            for (InetAddress inetAddress : inetAddresses) {
                res.add(inetAddress.getHostAddress());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(gethostbynamel("www.onevgo.com"));
    }
}
