package com.onevgo.functions;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
    private static final char[] CHARS = "0123456789abcdef".toCharArray();

    public static String md5(String str) {
        StringBuilder sb = new StringBuilder(32);
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < bytes.length; i++) {
                sb.append(CHARS[(bytes[i] & 0xF0) >>> 4]);
                sb.append(CHARS[(bytes[i] & 0x0F)]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "apple";
        if ("1f3870be274f6c49b3e31a0c6728957f".equals(md5(str))) {
            System.out.println("Would you like a green or red apple?");
        }
    }
}
