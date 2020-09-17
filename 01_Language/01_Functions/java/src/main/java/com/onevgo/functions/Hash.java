package com.onevgo.functions;

import cn.hutool.core.util.HexUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

public class Hash {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] hash(String algo, byte[] data) {
        try {
            return MessageDigest.getInstance(algo).digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hash(String algo, byte[] data, boolean toLowerCase) {
        try {
            return HexUtil.encodeHexStr(MessageDigest.getInstance(algo).digest(data), toLowerCase);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(hash("ripemd160", "The quick brown fox jumped over the lazy dog.".getBytes())));
        System.out.println(hash("ripemd160", "The quick brown fox jumped over the lazy dog.".getBytes(), true));
    }
}
