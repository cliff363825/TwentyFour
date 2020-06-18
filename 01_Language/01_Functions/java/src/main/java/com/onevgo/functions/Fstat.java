package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Fstat {
    public static void main(String[] args) throws IOException {
        Map<String, Object> map = Files.readAttributes(Paths.get("test.txt"), "*");
        Map<String, Object> map1 = Files.readAttributes(Paths.get("test.txt"), "unix:*");
        Map<String, Object> map2 = Files.readAttributes(Paths.get("test.txt"), "posix:*");
        System.out.println(map);
        System.out.println(map1);
        System.out.println(map2);
    }
}
