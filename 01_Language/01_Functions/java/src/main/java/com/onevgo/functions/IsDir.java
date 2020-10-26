package com.onevgo.functions;

import java.nio.file.Paths;

public class IsDir {
    public static boolean isDir(String filename) {
        return Paths.get(filename).toFile().isDirectory();
    }

    public static void main(String[] args) {
        System.out.println(isDir("test.txt"));
        System.out.println(isDir("test1/test2"));
        System.out.println(isDir(".."));
    }
}
