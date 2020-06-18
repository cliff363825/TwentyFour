package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class Filemtime {
    public static long filemtime(String filename) {
        try {
            return Files.getLastModifiedTime(Paths.get(filename)).toMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static void main(String[] args) {
        System.out.println(new Date(filemtime("test.txt")));
    }
}
