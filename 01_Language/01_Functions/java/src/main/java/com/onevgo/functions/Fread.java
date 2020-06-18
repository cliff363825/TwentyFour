package com.onevgo.functions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Fread {
    public static void main(String[] args) {
        FileReader reader = null;
        try {
            File file = new File("test.txt");
            long size = Files.size(Paths.get(file.toURI()));
            reader = new FileReader(file);
            char[] buf = new char[(int)size];
            int len = reader.read(buf);
            System.out.println(new String(buf, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
