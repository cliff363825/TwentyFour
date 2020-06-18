package com.onevgo.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ftell {
    public static void main(String[] args) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(new File("test.txt"), "r");
            byte[] b = new byte[12];
            int i = randomAccessFile.read(b);
            System.out.println(new String(b, 0, i));
            long filePointer = randomAccessFile.getFilePointer();
            System.out.println(filePointer);
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
