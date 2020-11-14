package com.onevgo.functions;

import java.nio.file.Files;
import java.nio.file.Paths;

public class IsReadable {
    public static boolean isReadable(String filename) {
        return Files.isReadable(Paths.get(filename));
    }

    public static void main(String[] args) {
        System.out.println(isReadable("test.txt"));
    }
}
