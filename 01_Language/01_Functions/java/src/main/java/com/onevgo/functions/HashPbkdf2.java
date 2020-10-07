package com.onevgo.functions;

import cn.hutool.crypto.SecureUtil;
import com.google.common.io.BaseEncoding;

import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;

public class HashPbkdf2 {
    private final static int HASH_BYTES = 32;

    public static byte[] hashPbkdf2(String algo, char[] password, byte[] salt, int iterations) {
        return hashPbkdf2(algo, password, salt, iterations, HASH_BYTES);
    }

    public static byte[] hashPbkdf2(String algo, char[] password, byte[] salt, int iterations, int length) {
        SecretKey secretKey = SecureUtil.generateKey(algo, new PBEKeySpec(password, salt, iterations, length * 8));
        return secretKey.getEncoded();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        char[] password = "password".toCharArray();
        int iterations = 1000;
        byte[] salt = new byte[16];

//        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
//        secureRandom.nextBytes(salt);
//        System.out.println(Arrays.toString(salt));
        salt = "abcdef".getBytes();

        byte[] bytes = hashPbkdf2("PBKDF2WithHmacSHA256", password, salt, iterations);
        System.out.println(BaseEncoding.base16().encode(bytes).toLowerCase());

        bytes = hashPbkdf2("PBKDF2WithHmacSHA256", password, salt, iterations, 10);
        System.out.println(BaseEncoding.base16().encode(bytes).toLowerCase());
    }
}
