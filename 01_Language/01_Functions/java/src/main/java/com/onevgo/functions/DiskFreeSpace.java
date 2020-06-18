package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DiskFreeSpace {
    public static long diskFreeSpace(String directory) {
        try {
            FileStore fileStore = Files.getFileStore(Paths.get(directory));
            return fileStore.getUsableSpace();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(diskFreeSpace("/"));
    }
}
