package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Readlink {
    public static String readlink(String path) {
        try {
            return Files.readSymbolicLink(Paths.get(path)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(readlink("/usr/local/bin/python3"));
    }
}
