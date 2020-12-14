package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Copy {
    public static boolean copy(String source, String dest) {
        try {
            Files.copy(Paths.get(source), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(copy("test.txt", "test.txt.bak"));
    }
}
