package com.onevgo.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilePutContents {
    public static void filePutContents(String filename, byte[] data) {
        filePutContents(filename, data, false);
    }

    public static void filePutContents(String filename, byte[] data, boolean append) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(filename), append);
            outputStream.write(data);
            outputStream.flush();
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
        }
    }

    public static void main(String[] args) {
        filePutContents("test.txt", "John Smith\n".getBytes(), true);
        System.out.println(FileGetContents.fileGetContents("test.txt"));
    }
}
