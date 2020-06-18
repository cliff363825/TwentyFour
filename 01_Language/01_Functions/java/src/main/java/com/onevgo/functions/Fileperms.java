package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Map;
import java.util.Set;

public class Fileperms {
    public static int fileperms(String filename) {
        try {
            Object mode = Files.getAttribute(Paths.get(filename), "unix:mode");
            if (mode != null && Integer.class.isAssignableFrom(mode.getClass())) {
                return ((Integer) mode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(fileperms("test.txt"));
        System.out.println(Integer.toOctalString(fileperms("test.txt")));
    }
}
