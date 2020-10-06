package com.onevgo.functions;

import com.google.common.io.BaseEncoding;

public class Bin2Hex {
    public static String bin2Hex(byte[] data) {
        return BaseEncoding.base16().encode(data);
    }

    public static void main(String[] args) {
        System.out.println(bin2Hex("中国".getBytes()));
    }
}
