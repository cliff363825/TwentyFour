package com.onevgo.functions;

import com.google.common.io.BaseEncoding;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Encode {
    public static String base64Encode(byte[] data) {
        return BaseEncoding.base64().encode(data);
    }

    public static void main(String[] args) {
        // java >= 8:
        System.out.println(Base64.getEncoder().encodeToString("This is an encoded string".getBytes(StandardCharsets.UTF_8)));

        // java < 8:
        System.out.println(base64Encode("This is an encoded string".getBytes(StandardCharsets.UTF_8)));
    }
}
