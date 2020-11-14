package com.onevgo.functions;

import java.nio.file.Files;
import java.nio.file.Paths;

public class IsExecutable {
    public static boolean isExecutable(String filename) {
        return Files.isExecutable(Paths.get(filename));
    }

    public static void main(String[] args) {
        System.out.println(isExecutable("test.sh"));
    }
}
