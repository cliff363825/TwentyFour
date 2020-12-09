package com.onevgo.functions;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.Digester;

import java.util.Arrays;

public class Hash {
    public static byte[] hash(String algo, byte[] data) {
        return new Digester(algo).digest(data);
    }

    public static String hash(String algo, byte[] data, boolean toLowerCase) {
        return HexUtil.encodeHexStr(hash(algo, data), toLowerCase);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(hash("ripemd160", "The quick brown fox jumped over the lazy dog.".getBytes())));
        System.out.println(hash("ripemd160", "The quick brown fox jumped over the lazy dog.".getBytes(), true));
    }
}
