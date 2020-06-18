package com.onevgo.functions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fputs {
    public static void main(String[] args) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("test.txt"), true);
            fileWriter.write("Add this to the file\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
