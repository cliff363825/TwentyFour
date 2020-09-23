package com.onevgo.functions;

import java.io.*;

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

    public static void filePutContents(String filename, String data) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(filename));
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void filePutContents(String filename, String data, boolean append) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(filename), append);
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
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
