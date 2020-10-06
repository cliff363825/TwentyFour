package com.onevgo.functions;

import com.google.common.io.BaseEncoding;

public class Hex2bin {
    public static byte[] hex2bin(String data) {
        return BaseEncoding.base16().decode(data);
    }

    public static void main(String[] args) {
        byte[] hex = hex2bin("6578616d706c65206865782064617461".toUpperCase());
        System.out.println(new String(hex));
    }
}
