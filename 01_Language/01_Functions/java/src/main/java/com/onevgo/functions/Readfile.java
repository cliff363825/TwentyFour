package com.onevgo.functions;

import java.io.*;

public class Readfile {
    public static void main(String[] args) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File("test.gif")));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[8192];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        System.out.println(outputStream.toString());
        outputStream.close();
        inputStream.close();
    }
}
