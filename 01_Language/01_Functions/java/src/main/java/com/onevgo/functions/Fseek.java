package com.onevgo.functions;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Fseek {
    public static void main(String[] args) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(new File("test.txt"), "r");
            System.out.println(randomAccessFile.readLine());
            System.out.println(randomAccessFile.readLine());
            randomAccessFile.seek(0);
            System.out.println(randomAccessFile.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
