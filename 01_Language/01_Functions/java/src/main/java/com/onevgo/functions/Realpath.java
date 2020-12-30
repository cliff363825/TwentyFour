package com.onevgo.functions;

import java.io.File;
import java.io.IOException;

public class Realpath {
    public static String realpath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return "";
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return file.getAbsolutePath();
        }
    }

    public static void main(String[] args) {
        System.out.println(realpath("./"));
        System.out.println(realpath("/tmp/"));
        System.out.println(realpath("/aaa"));
    }
}
