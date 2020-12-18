package com.onevgo.functions;

import cn.hutool.core.util.HexUtil;

public class Bin2Hex {
    public static String bin2Hex(byte[] data) {
        return HexUtil.encodeHexStr(data);
    }

    public static void main(String[] args) {
        System.out.println(bin2Hex("中国".getBytes()));
    }
}
