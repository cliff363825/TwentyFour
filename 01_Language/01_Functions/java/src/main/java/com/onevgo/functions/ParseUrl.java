package com.onevgo.functions;

import java.net.URL;

public class ParseUrl {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://username:password@hostname:9090/path?arg=value#anchor");
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getUserInfo());
        System.out.println(url.getPath());
        System.out.println(url.getQuery());
        System.out.println(url.toURI().getFragment());
    }
}
