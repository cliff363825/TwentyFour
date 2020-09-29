package com.onevgo.functions;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class FilePutContents {
    public static int filePutContents(String filename, byte[] data, FileWriteMode... flags) {
        try {
            Files.asByteSink(new File(filename), flags).write(data);
            return data.length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int filePutContents(String filename, String data, FileWriteMode... flags) {
        return filePutContents(filename, data.getBytes(), flags);
    }

    public static void main(String[] args) {
        filePutContents("test.txt", "John Smith\n".getBytes(), FileWriteMode.APPEND);
        System.out.println(FileGetContents.fileGetContents("test.txt"));
    }
}
