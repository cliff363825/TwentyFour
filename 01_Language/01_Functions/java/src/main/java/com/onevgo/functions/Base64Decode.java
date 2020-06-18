package com.onevgo.functions;

import java.nio.charset.Charset;
import java.util.Base64;

public class Base64Decode {

    public static void main(String[] args) {
        // java >= 8:
        byte[] decode = Base64.getDecoder().decode("VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw==");
        System.out.println(new String(decode, Charset.forName("UTF-8")));
        // java < 8:
        System.out.println(cn.hutool.core.codec.Base64.decodeStr("VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw=="));
    }
}
