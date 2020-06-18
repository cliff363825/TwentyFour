package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

public class Filegroup {
    public static int filegroup(String filename) {
        try {
            Object gid = Files.getAttribute(Paths.get(filename), "unix:gid");
            if (gid != null && Integer.class.isAssignableFrom(gid.getClass())) {
                return (Integer) gid;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(filegroup("test.txt"));
    }
}
