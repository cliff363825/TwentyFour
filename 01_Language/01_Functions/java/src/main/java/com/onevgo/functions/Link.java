package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Link {
    public static boolean link(String target, String link) {
        try {
            Files.createLink(Paths.get(link), Paths.get(target));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        link("test.txt", "test1.txt");
    }
}
