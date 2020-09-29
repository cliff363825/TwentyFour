package com.onevgo.functions;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class Copy {
    public static boolean copy(String source, String dest) {
        try {
            Files.copy(new File(source), new File(dest));
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
