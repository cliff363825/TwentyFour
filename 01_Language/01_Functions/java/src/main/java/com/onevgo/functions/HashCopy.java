package com.onevgo.functions;

import cn.hutool.core.util.HexUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCopy {
    public static MessageDigest hashCopy(MessageDigest context) {
        try {
            Object copy = context.clone();
            if (MessageDigest.class.isAssignableFrom(copy.getClass())) {
                return (MessageDigest) copy;
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5.update("data".getBytes());

        MessageDigest md5_copy = hashCopy(md5);

        md5_copy.update("data".getBytes());

        System.out.println(HexUtil.encodeHexStr(md5.digest()));
        System.out.println(HexUtil.encodeHexStr(md5_copy.digest()));

        md5.update("datadata".getBytes());
        System.out.println(HexUtil.encodeHexStr(md5.digest()));
    }
}
