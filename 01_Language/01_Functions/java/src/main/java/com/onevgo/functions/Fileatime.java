package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class Fileatime {
    // java 7+
    public static long fileatime(String filename) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(Paths.get(filename), BasicFileAttributes.class);
            return attrs.lastAccessTime().toMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static void main(String[] args) {
        System.out.println(new Date(fileatime("test.txt")));
    }
}
