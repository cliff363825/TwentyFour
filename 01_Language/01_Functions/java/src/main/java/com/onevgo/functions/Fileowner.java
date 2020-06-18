package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Fileowner {
    public static int fileowner(String filename) {
        try {
            Object uid = Files.getAttribute(Paths.get(filename), "unix:uid");
            if (uid != null && Integer.class.isAssignableFrom(uid.getClass())) {
                return ((Integer) uid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(fileowner("test.txt"));
    }
}
