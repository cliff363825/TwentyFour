package com.onevgo.functions;

import cn.hutool.core.util.RandomUtil;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.params.HKDFParameters;

import java.util.Arrays;

public class HashHkdf {
    public static byte[] hashHkdf(Digest algo, byte[] ikm, int length, byte[] info, byte[] salt) {
        HKDFBytesGenerator hkdf = new HKDFBytesGenerator(algo);
        hkdf.init(new HKDFParameters(ikm, salt, info));
        if (length == 0) {
            length = hkdf.getDigest().getDigestSize();
        }
        byte[] okm = new byte[length];
        hkdf.generateBytes(okm, 0, length);
        return okm;
    }

    public static byte[] hashHkdf(Digest algo, byte[] ikm) {
        return hashHkdf(algo, ikm, 0, null, null);
    }

    public static void main(String[] args) {
//        byte[] inputKey = RandomUtil.randomBytes(32);
        byte[] inputKey = "abcdef".getBytes();
//        byte[] salt = RandomUtil.randomBytes(32);
        byte[] salt = "abcdef".getBytes();

        byte[] encryptionKey = hashHkdf(new SHA256Digest(), inputKey, 32, "aes-256-encryption".getBytes(), salt);
        byte[] authenticationKey = hashHkdf(new SHA256Digest(), inputKey, 32, "sha-256-authentication".getBytes(), salt);

        System.out.println(new String(encryptionKey));
        System.out.println(new String(authenticationKey));

        System.out.println(Arrays.equals(encryptionKey, authenticationKey));
    }
}
