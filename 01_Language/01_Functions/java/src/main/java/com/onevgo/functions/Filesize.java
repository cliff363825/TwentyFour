package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Filesize {
    public static long filesize(String filename) {
        try {
            return Files.size(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static void main(String[] args) {
        System.out.println(filesize("test.txt"));
    }
}
