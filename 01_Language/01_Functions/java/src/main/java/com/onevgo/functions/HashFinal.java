package com.onevgo.functions;

import cn.hutool.core.util.HexUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFinal {
    public static String hashFinal(MessageDigest digest) {
        return HexUtil.encodeHexStr(digest.digest());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("sha1");
        sha1.update("The quick brown fox jumped over the lazy dog.".getBytes());
        System.out.println(hashFinal(sha1));
    }
}
