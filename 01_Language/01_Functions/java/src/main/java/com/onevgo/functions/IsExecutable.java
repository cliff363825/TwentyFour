package com.onevgo.functions;

import java.nio.file.Paths;

public class IsExecutable {
    public static boolean isExecutable(String filename) {
        return Paths.get(filename).toFile().canExecute();
    }

    public static void main(String[] args) {
        System.out.println(isExecutable("test.sh"));
    }
}
