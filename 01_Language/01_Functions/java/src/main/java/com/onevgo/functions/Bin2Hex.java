package com.onevgo.functions;

import com.google.common.io.BaseEncoding;

import java.math.BigInteger;
import java.util.Arrays;

public class Bin2Hex {
    public static String bin2Hex(byte[] data) {
        return BaseEncoding.base16().encode(data);
    }

    public static void main(String[] args) {
        System.out.println(bin2Hex("中国".getBytes()));

        System.out.println(Arrays.toString(new BigInteger("1100001", 2).toByteArray()));
    }
}
