package com.onevgo.functions;

import cn.hutool.crypto.digest.HMac;

import java.io.File;
import java.io.IOException;

public class HashHmacFile {
    public static String hashHmacFile(String algo, String filename, byte[] key) {
        return new HMac(algo, key).digestHex(new File(filename));
    }

    public static void main(String[] args) throws IOException {
        FilePutContents.filePutContents("test.txt", "The quick brown fox jumped over the lazy dog.");
        System.out.println(hashHmacFile("HMACMD5", "test.txt", "secret".getBytes()));
    }
}
