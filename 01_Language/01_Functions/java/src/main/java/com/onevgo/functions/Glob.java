package com.onevgo.functions;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Glob {
    public static List<String> glob(String pattern, String location) {
        List<String> result = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        try {
            Files.walkFileTree(Paths.get(location), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(file)) {
                        result.add(file.toFile().getName());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> glob(String pattern) {
        return glob(pattern, "");
    }

    public static void main(String[] args) {
        System.out.println(glob("*.txt"));
    }
}
