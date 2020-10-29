package com.onevgo.functions;

import java.nio.file.Paths;

public class IsReadable {
    public static boolean isReadable(String filename) {
        return Paths.get(filename).toFile().canRead();
    }

    public static void main(String[] args) {
        System.out.println(isReadable("test.txt"));
    }
}
