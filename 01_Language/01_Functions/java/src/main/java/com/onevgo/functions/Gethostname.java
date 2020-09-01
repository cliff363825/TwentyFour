package com.onevgo.functions;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Gethostname {
    public static String gethostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(gethostname());
    }
}

