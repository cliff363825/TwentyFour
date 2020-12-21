package com.onevgo.functions;

import cn.hutool.core.util.HexUtil;

public class Hex2bin {
    public static byte[] hex2bin(String data) {
        return HexUtil.decodeHex(data);
    }

    public static void main(String[] args) {
        byte[] hex = hex2bin("6578616d706c65206865782064617461".toUpperCase());
        System.out.println(new String(hex));
    }
}
