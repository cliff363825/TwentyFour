package com.onevgo.functions;

import cn.hutool.core.io.FileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Basename {
    public static String basename(String path) {
        String res = "";
        Path fileName = Paths.get(path).getFileName();
        if (fileName != null) {
            res = fileName.toString();
        }
        return res;
    }

    public static String basename(String path, String suffix) {
        String res = basename(path);
        if (res.endsWith(suffix)) {
            res = res.substring(0, res.length() - suffix.length());
        }
        return res;
    }

    public static void main(String[] args) {
        // java 7+:
        System.out.println(basename("/etc/sudoers.d", ".d"));
        System.out.println(basename("/etc/sudoers.d"));
        System.out.println(basename("/etc/passwd"));
        System.out.println(basename("/etc/passwd?a=b"));
        System.out.println(basename("/etc/"));
        System.out.println(basename("."));
        System.out.println(basename("/"));

        System.out.println(FileUtil.getName("/etc/sudoers.d"));
        System.out.println(FileUtil.getName("/etc/passwd"));
        System.out.println(FileUtil.getName("/etc/"));
        System.out.println(FileUtil.getName("."));
        System.out.println(FileUtil.getName("/"));

        System.out.println(FileUtil.mainName("/etc/sudoers.d"));
        System.out.println(FileUtil.mainName("/etc/passwd"));
        System.out.println(FileUtil.mainName("/etc/"));
        System.out.println(FileUtil.mainName("."));
        System.out.println(FileUtil.mainName("/"));
    }
}
