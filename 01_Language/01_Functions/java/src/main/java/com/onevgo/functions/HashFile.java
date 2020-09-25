package com.onevgo.functions;

import cn.hutool.crypto.digest.Digester;

import java.io.File;

public class HashFile {
    public static String hashFile(String algo, String filename) {
        return new Digester(algo).digestHex(new File(filename));
    }

    public static void main(String[] args) {
        FilePutContents.filePutContents("test.txt", "The quick brown fox jumped over the lazy dog.");
        System.out.println(hashFile("md5", "test.txt"));
    }
}
