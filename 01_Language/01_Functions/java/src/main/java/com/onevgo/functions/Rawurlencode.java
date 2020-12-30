package com.onevgo.functions;

import cn.hutool.core.net.URLEncoder;

import java.nio.charset.StandardCharsets;

public class Rawurlencode {
    private static final URLEncoder URL_ENCODER_RFC_3986;

    static {
        URL_ENCODER_RFC_3986 = new URLEncoder();
        URL_ENCODER_RFC_3986.addSafeCharacter('-');
        URL_ENCODER_RFC_3986.addSafeCharacter('_');
        URL_ENCODER_RFC_3986.addSafeCharacter('.');
        URL_ENCODER_RFC_3986.addSafeCharacter('~');
    }

    public static String rawurlencode(String str) {
        return URL_ENCODER_RFC_3986.encode(str, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        System.out.println(rawurlencode("foo bar@baz"));
        System.out.println(rawurlencode("foo+bar@baz"));
        System.out.println(rawurlencode("+*~"));
    }
}
