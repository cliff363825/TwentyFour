package com.onevgo.functions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fwrite {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter(new File("test.txt"), true)) {
            fileWriter.write("Add this to the file\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
