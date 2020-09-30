package com.onevgo.functions;

import cn.hutool.crypto.digest.Digester;
import com.google.common.io.BaseEncoding;

import java.util.Arrays;

public class Hash {
    public static byte[] hash(String algo, byte[] data) {
        return new Digester(algo).digest(data);
    }

    public static String hash(String algo, byte[] data, boolean toLowerCase) {
        String result = BaseEncoding.base16().encode(new Digester(algo).digest(data));
        return toLowerCase ? result.toLowerCase() : result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(hash("ripemd160", "The quick brown fox jumped over the lazy dog.".getBytes())));
        System.out.println(hash("ripemd160", "The quick brown fox jumped over the lazy dog.".getBytes(), true));
    }
}
