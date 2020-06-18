package com.onevgo.functions;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("").getAbsoluteFile();
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        for (File f : file.listFiles()) {
            System.out.println(f.getAbsolutePath());
        }
    }
}
