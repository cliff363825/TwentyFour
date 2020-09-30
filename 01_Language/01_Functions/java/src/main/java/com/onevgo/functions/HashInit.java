package com.onevgo.functions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashInit {
    public static MessageDigest hashInit(String algo) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algo);
            return digest;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        MessageDigest md5 = hashInit("md5");
        md5.update("The quick brown fox ".getBytes());
        md5.update("jumped over the lazy dog.".getBytes());
        System.out.println(HashFinal.hashFinal(md5));
    }
}
