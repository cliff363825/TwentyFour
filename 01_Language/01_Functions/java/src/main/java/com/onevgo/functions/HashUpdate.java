package com.onevgo.functions;

import java.security.MessageDigest;

public class HashUpdate {
    public static boolean hashUpdate(MessageDigest context, byte[] data) {
        context.update(data);
        return true;
    }

    public static void main(String[] args) {

    }
}
