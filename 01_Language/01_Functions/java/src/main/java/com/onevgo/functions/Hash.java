package com.onevgo.functions;

import cn.hutool.crypto.digest.Digester;
import com.google.common.io.BaseEncoding;

import java.util.Arrays;

public class Hash {
    public static byte[] hash(String algo, byte[] data) {
        return new Digester(algo).digest(data);
    }

    public static String hash(String algo, byte[] data, boolean toLowerCase) {
        BaseEncoding baseEncoding = toLowerCase ? BaseEncoding.base16().lowerCase() : BaseEncoding.base16().upperCase();
        return baseEncoding.encode(new Digester(algo).digest(data));
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(hash("ripemd160", "The quick brown fox jumped over the lazy dog.".getBytes())));
        System.out.println(hash("ripemd160", "The quick brown fox jumped over the lazy dog.".getBytes(), true));
    }
}
