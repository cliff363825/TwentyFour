package com.onevgo.functions;

import com.google.common.io.BaseEncoding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFinal {
    public static String hashFinal(MessageDigest digest) {
        return BaseEncoding.base16().encode(digest.digest()).toLowerCase();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("sha1");
        sha1.update("The quick brown fox jumped over the lazy dog.".getBytes());
        System.out.println(hashFinal(sha1));
    }
}
