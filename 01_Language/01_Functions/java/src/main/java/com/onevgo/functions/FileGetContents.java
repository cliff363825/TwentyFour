package com.onevgo.functions;

import java.io.*;
import java.net.URL;

public class FileGetContents {
    public static String fileGetContents(String filename) {
        if (filename == null || filename.length() == 0) return null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            if ("http://".equalsIgnoreCase(filename.substring(0, 7)) || "https://".equalsIgnoreCase(filename.substring(0, 8))) {
                inputStream = new URL(filename).openConnection().getInputStream();
            } else {
                inputStream = new FileInputStream(new File(filename));
            }
            outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buf = new byte[8192];
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            return outputStream.toString("utf-8");
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
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(fileGetContents("test.txt"));
        System.out.println(fileGetContents("https://www.onevgo.com"));
    }
}
