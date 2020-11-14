package com.onevgo.functions;

import java.nio.file.Files;
import java.nio.file.Paths;

public class IsFile {
    public static boolean isFile(String filename) {
        return Files.isRegularFile(Paths.get(filename));
    }

    public static void main(String[] args) {
        System.out.println(isFile("test.txt"));
        System.out.println(isFile("/usr/bin/"));
    }
}
