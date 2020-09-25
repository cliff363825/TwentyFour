package com.onevgo.functions;

import cn.hutool.crypto.digest.HMac;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class HashHmac {
//    static {
//        Security.addProvider(new BouncyCastleProvider());
//    }
//
//    public static String hashHmacOld(String algo, byte[] data, byte[] key) {
//        try {
//            Mac mac = Mac.getInstance(algo);
//            SecretKeySpec keySpec = new SecretKeySpec(key, algo);
//            mac.init(keySpec);
//            return HexUtil.encodeHexStr(mac.doFinal(data));
//        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static String hashHmac(String algo, byte[] data, byte[] key) {
        return new HMac(algo, key).digestHex(data);
    }

    public static void main(String[] args) {
        System.out.println(hashHmac("HmacRipemd160", "The quick brown fox jumped over the lazy dog.".getBytes(), "secret".getBytes()));
    }
}
