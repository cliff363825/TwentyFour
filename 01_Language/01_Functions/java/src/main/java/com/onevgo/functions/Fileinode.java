package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Fileinode {
    public static long fileinode(String filename) {
        try {
            Object ino = Files.getAttribute(Paths.get(filename), "unix:ino");
            if (ino != null && Long.class.isAssignableFrom(ino.getClass())) {
                return ((Long) ino);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static void main(String[] args) {
        System.out.println(fileinode("test.txt"));
    }
}
