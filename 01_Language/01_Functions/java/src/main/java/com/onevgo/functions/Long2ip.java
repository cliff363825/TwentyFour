package com.onevgo.functions;

public class Long2ip {
    public static String long2ip(long properAddress) {
        return (properAddress >>> 24 & 0xFF) + "."
                + (properAddress >>> 16 & 0xFF) + "."
                + (properAddress >>> 8 & 0xFF) + "."
                + (properAddress & 0xFF);
    }

    public static void main(String[] args) {
        System.out.println(Ip2long.ip2long("193.112.23.137"));
        System.out.println(long2ip(3245348745L));
    }
}
