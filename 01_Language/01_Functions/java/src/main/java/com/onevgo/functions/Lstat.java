package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Lstat {
    public static Map<String, Object> lstat(String filename) {
        try {
            return Files.readAttributes(Paths.get(filename), "unix:*");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Link.link("test.txt", "test"));
        System.out.println(lstat("test"));
    }
}
