package com.onevgo.j2se.classloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExampleClassLoader extends ClassLoader {
    private int key;
    private String rootPath;

    public ExampleClassLoader(int key, String rootPath) {
        this.key = key;
        this.rootPath = rootPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = loadClassBytes(name);
            Class<?> cl = defineClass(name, bytes, 0, bytes.length);
            if (cl == null) {
                throw new ClassNotFoundException(name);
            }
            return cl;
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }

    private byte[] loadClassBytes(String name) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(rootPath, name.replace('.', '/') + ".caesar"));
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - key);
        }
        return bytes;
    }
}
