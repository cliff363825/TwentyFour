package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public class Filectime {
    // java 7+
    public static long filectime(String filename) {
        try {
            //Map<String, Object> map = Files.readAttributes(Paths.get(filename), "unix:*");
            Object ctime = Files.getAttribute(Paths.get(filename), "unix:ctime");
            if (ctime != null && FileTime.class.isAssignableFrom(ctime.getClass())) {
                return ((FileTime) ctime).toMillis();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Date(filectime("test.txt")));
    }
}
