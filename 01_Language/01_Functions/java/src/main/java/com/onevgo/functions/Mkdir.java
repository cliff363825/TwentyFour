package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Mkdir {
    public static boolean mkdir(String pathname) {
        try {
            Files.createDirectories(Paths.get(pathname));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(mkdir("./depth1/depth2/depth3/"));
    }
}
