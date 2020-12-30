package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Readdir {
    public static void main(String[] args) throws IOException {
        Files.list(Paths.get("/Users")).forEach(p -> {
            System.out.println(p.getFileName());
        });
    }
}
