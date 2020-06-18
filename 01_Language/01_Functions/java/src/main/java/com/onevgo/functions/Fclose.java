package com.onevgo.functions;

import java.io.*;

public class Fclose {
    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    System.out.println("Close!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
