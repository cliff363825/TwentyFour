package com.onevgo.functions;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Copy {
    public static boolean copy(String srcPath, String destPath) {
        return copy(srcPath, destPath, true);
    }

    public static boolean copy(String srcPath, String destPath, boolean isOverride) {
        try {
            FileUtil.copy(srcPath, destPath, isOverride);
            return true;
        } catch (IORuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copy(File src, File dest) {
        return copy(src, dest, true);
    }

    public static boolean copy(File src, File dest, boolean isOverride) {
        try {
            FileUtil.copy(src, dest, isOverride);
            return true;
        } catch (IORuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // java 7+:
        String resourcePath = Copy.class.getResource("/").getPath();
        File srcFile = new File(resourcePath, "test.txt");
        File destFile = new File(resourcePath, "test.txt.bak");
        try {
            Files.copy(Paths.get(srcFile.getPath()), Paths.get(destFile.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(copy("test.txt", "test.txt.bak"));
    }
}
