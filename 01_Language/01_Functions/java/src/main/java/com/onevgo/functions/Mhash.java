package com.onevgo.functions;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.HMac;

public class Mhash {
    public static byte[] mhash(String hash, byte[] data) {
        return new Digester(hash).digest(data);
    }

    public static byte[] mhash(String hash, byte[] data, byte[] key) {
        return new HMac(hash, key).digest(data);
    }

    public static void main(String[] args) {
        System.out.println(HexUtil.encodeHex(mhash("md5", "admin".getBytes())));
        System.out.println(HexUtil.encodeHex(mhash("HmacMD5", "admin".getBytes(), "123".getBytes())));
    }
}
