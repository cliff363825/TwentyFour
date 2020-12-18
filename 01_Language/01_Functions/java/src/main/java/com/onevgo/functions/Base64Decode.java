package com.onevgo.functions;

import cn.hutool.core.codec.Base64;

public class Base64Decode {
    public static byte[] base64Decode(CharSequence data) {
        return Base64.decode(data);
    }

    public static String base64DecodeStr(CharSequence data) {
        return Base64.decodeStr(data);
    }

    public static void main(String[] args) {
        System.out.println(base64DecodeStr("VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw=="));
    }
}
