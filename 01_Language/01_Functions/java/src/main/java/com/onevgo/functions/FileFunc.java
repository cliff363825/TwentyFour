package com.onevgo.functions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileFunc {
    public static List<String> file(String filename) {
        BufferedReader reader = null;
        try {
            List<String> res = new ArrayList<>();
            reader = new BufferedReader(new FileReader(new File(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                res.add(line);
            }
            return res;
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
        return null;
    }

    public static void main(String[] args) {
        System.out.println(file("test.txt"));
    }
}
