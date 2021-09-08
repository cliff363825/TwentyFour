package com.onevgo.j2se.classloader;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        int key = 3;
//        caesar(key);
        loadClass(key);
    }

    private static void caesar(int key) {
        try (FileInputStream fileInputStream = new FileInputStream("j2se/target/classes/com/onevgo/j2se/classloader/ExampleCaesar.class");
             FileOutputStream fileOutputStream = new FileOutputStream("j2se/src/main/java/com/onevgo/j2se/classloader/ExampleCaesar.caesar")) {
            int b;
            while ((b = fileInputStream.read()) != -1) {
                byte b1 = ((byte) (b + key));
                fileOutputStream.write(b1);
            }
        } catch (IOException e) {
            log.error("error=", e);
        }
    }

    private static void loadClass(int key) {
        ExampleClassLoader classLoader = new ExampleClassLoader(key, "j2se/src/main/java");
        Class<?> cl = null;
        try {
            cl = classLoader.loadClass("com.onevgo.j2se.classloader.ExampleCaesar");
        } catch (ClassNotFoundException e) {
            log.error("error=", e);
        }
        log.info("{}", cl);
    }
}
