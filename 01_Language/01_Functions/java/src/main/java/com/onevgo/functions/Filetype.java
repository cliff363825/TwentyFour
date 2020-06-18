package com.onevgo.functions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum Filetype {
    FIFO,
    CHAR,
    DIR,
    BLOCK,
    LINK,
    FILE,
    SOCKET,
    UNKNOWN;

    public static Filetype filetype(String filename) {
        try {
            Path path = Paths.get(filename);
            if (Files.isDirectory(path)) {
                return DIR;
            } else if (Files.isSymbolicLink(path)) {
                return LINK;
            } else if (Files.isRegularFile(path)) {
                return FILE;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return UNKNOWN;
    }

    public static void main(String[] args) {
        System.out.println(filetype("/dev/urandom"));
        System.out.println(filetype("/dev/"));
        System.out.println(filetype("/dev/disk0"));
        System.out.println(filetype("/dev/stdin"));
        System.out.println(filetype("/etc/passwd"));
        System.out.println(filetype("/var/run/syslog"));
    }
}
