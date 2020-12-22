package com.onevgo.functions;

import com.google.common.io.Files;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Pathinfo {
    public static Map<String, String> pathinfo(String path) {
        HashMap<String, String> map = new HashMap<>();
        File file = new File(path);
        map.put("dirname", file.getParent());
        map.put("basename", file.getName());

        String extension = "";
        String filename = file.getName();
        int dotPos = file.getName().lastIndexOf('.');
        if (dotPos != -1) {
            extension = filename.substring(dotPos + 1);
            filename = filename.substring(0, dotPos);
        }
        map.put("extension", extension);
        map.put("filename", filename);
        return map;
    }

    public static void main(String[] args) {
        System.out.println(pathinfo("./test.txt"));
    }
}
