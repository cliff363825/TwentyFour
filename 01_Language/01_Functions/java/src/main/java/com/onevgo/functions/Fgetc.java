package com.onevgo.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Fgetc {
    public static void main(String[] args) {
        File file = new File("test.txt");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            int b;
            while ((b = fileInputStream.read()) != -1) {
                System.out.println(Character.valueOf((char) b));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
