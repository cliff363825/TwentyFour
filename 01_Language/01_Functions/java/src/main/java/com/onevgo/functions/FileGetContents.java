package com.onevgo.functions;

import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileGetContents {
    public static byte[] fileGetContents(String filename) {
        byte[] data = null;
        try {
            if ("http://".equalsIgnoreCase(filename.substring(0, 7)) || "https://".equalsIgnoreCase(filename.substring(0, 8))) {
                data = Resources.asByteSource(new URL(filename)).read();
            } else {
                data = Files.asByteSource(new File(filename)).read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String fileGetContents(String filename, Charset character) {
        byte[] data = fileGetContents(filename);
        return data == null ? null : new String(data, character);
    }

    public static void main(String[] args) {
        System.out.println(fileGetContents("test.txt", StandardCharsets.UTF_8));
        System.out.println(fileGetContents("https://www.onevgo.com", StandardCharsets.UTF_8));
    }
}
