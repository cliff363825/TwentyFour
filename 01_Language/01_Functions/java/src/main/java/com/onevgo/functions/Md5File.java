package com.onevgo.functions;

import cn.hutool.crypto.SecureUtil;

import java.io.File;

public class Md5File {
    public static String md5File(String filename) {
        return SecureUtil.md5(new File(filename));
    }

    public static void main(String[] args) {
        String file = "test.txt";
        System.out.println(md5File(file));
    }
}
