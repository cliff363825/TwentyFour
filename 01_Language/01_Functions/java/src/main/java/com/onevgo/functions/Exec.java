package com.onevgo.functions;

import cn.hutool.core.util.RuntimeUtil;

import java.io.*;

public class Exec {
    public static String exec(String command) {
        Process process = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            process = Runtime.getRuntime().exec(command);
            inputStream = process.getInputStream();
            outputStream = new ByteArrayOutputStream();
            int len;
            byte[] b = new byte[8192];
            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            return outputStream.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        System.out.println(exec("whoami"));
        System.out.println(RuntimeUtil.execForStr("whoami"));
        System.out.println(RuntimeUtil.execForStr("howareyou"));
    }
}
