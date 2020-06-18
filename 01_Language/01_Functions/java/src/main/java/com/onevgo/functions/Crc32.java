package com.onevgo.functions;

import java.util.zip.CRC32;

public class Crc32 {
    public static void main(String[] args) {
        CRC32 crc32 = new CRC32();
        crc32.update("The quick brown fox jumped over the lazy dog.".getBytes());
        System.out.println(crc32.getValue());
    }
}
