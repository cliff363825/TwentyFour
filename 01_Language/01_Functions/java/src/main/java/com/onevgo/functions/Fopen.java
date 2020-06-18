package com.onevgo.functions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Fopen {
    public static void main(String[] args) {
        FileReader reader = null;
        try {
            reader = new FileReader(new File("test.txt"));
            char[] buf = new char[8192];
            int len;
            if ((len = reader.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
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
