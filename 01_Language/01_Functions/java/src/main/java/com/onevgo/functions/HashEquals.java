package com.onevgo.functions;

import java.security.MessageDigest;

public class HashEquals {
    public static boolean hashEquals(String knownString, String userString) {
        return hashEquals(knownString.getBytes(), userString.getBytes());
    }

    public static boolean hashEquals(byte[] knownString, byte[] userString) {
        return MessageDigest.isEqual(knownString, userString);
    }

    public static void main(String[] args) {
        String expected = Crypt.crypt("12345", "$2a$07$usesomesillystringforsalt$");
        String correct = Crypt.crypt("12345", "$2a$07$usesomesillystringforsalt$");
        String incorrect = Crypt.crypt("apple", "$2a$07$usesomesillystringforsalt$");

        System.out.println(hashEquals(expected, correct));
        System.out.println(hashEquals(expected, incorrect));
    }
}
