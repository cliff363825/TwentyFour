package com.onevgo.functions;

import cn.hutool.crypto.digest.HMac;

public class HashHmac {
    public static String hashHmac(String algo, byte[] data, byte[] key) {
        return new HMac(algo, key).digestHex(data);
    }

    public static void main(String[] args) {
        System.out.println(hashHmac("HmacRipemd160", "The quick brown fox jumped over the lazy dog.".getBytes(), "secret".getBytes()));
    }
}
