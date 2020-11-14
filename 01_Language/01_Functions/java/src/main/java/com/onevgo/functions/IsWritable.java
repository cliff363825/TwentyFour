package com.onevgo.functions;

import java.nio.file.Files;
import java.nio.file.Paths;

public class IsWritable {
    public static boolean isWritable(String filename) {
        return Files.isWritable(Paths.get(filename));
    }

    public static void main(String[] args) {
        System.out.println(isWritable("test.txt"));
    }
}
