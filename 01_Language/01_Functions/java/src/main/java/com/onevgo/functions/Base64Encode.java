package com.onevgo.functions;

import cn.hutool.core.codec.Base64;

public class Base64Encode {
    public static String base64Encode(byte[] data) {
        return Base64.encode(data);
    }

    public static void main(String[] args) {
        System.out.println(base64Encode("This is an encoded string".getBytes()));
    }
}
