package com.onevgo.functions;

import com.google.common.io.BaseEncoding;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Decode {
    public static byte[] base64Decode(String data) {
        return BaseEncoding.base64().decode(data);
    }

    public static void main(String[] args) {
        // java >= 8:
        byte[] decode = Base64.getDecoder().decode("VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw==");
        System.out.println(new String(decode, StandardCharsets.UTF_8));

        // java < 8:
        byte[] decode1 = base64Decode("VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw==");
        System.out.println(new String(decode1, StandardCharsets.UTF_8));
    }
}
