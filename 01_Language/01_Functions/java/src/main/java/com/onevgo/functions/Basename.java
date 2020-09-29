package com.onevgo.functions;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Basename {
    public static String basename(String path) {
        Path fileName = Paths.get(path).getFileName();
        return fileName == null ? "" : fileName.toString();
    }

    public static String basename(String path, String suffix) {
        String result = basename(path);
        if (result.endsWith(suffix)) {
            result = result.substring(0, result.length() - suffix.length());
        }
        return result;
    }

    public static void main(String[] args) {
        // java 7+:
        System.out.println(basename("/etc/sudoers.d", ".d"));
        System.out.println(basename("/etc/sudoers.d"));
        System.out.println(basename("/etc/passwd"));
        System.out.println(basename("/etc/passwd?a=b"));
        System.out.println(basename("/etc/"));
        System.out.println(basename("."));
        System.out.println(basename("/"));
    }
}
