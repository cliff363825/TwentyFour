package com.onevgo.functions;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Encode {
    public static void main(String[] args) {
        // java >= 8:
        System.out.println(Base64.getEncoder().encodeToString("This is an encoded string".getBytes(StandardCharsets.UTF_8)));
        // java < 8:
        System.out.println(cn.hutool.core.codec.Base64.encode("This is an encoded string"));
    }
}
