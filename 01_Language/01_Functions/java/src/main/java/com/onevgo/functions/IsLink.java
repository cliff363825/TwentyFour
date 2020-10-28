package com.onevgo.functions;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class IsLink {
    public static boolean isLink(String filename) {
        try {
            return FileUtils.isSymlink(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isLink("test.txt"));
    }
}
