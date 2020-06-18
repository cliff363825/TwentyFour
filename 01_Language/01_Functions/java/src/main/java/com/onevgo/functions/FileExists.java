package com.onevgo.functions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileExists {
    public static void main(String[] args) {
        System.out.println(new File("test.txt").exists());
        // java 7+:
        System.out.println(Files.exists(Paths.get("test.txt")));
    }
}
