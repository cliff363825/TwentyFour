package io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class TestRandomAccessFile {
    @Before
    public void setUp() throws Exception {
        String s = "# 好脑子不如烂笔头系列之java\n" +
                "===========================\n" +
                "1. [JavaSE](README-JAVASE.md)\n" +
                "2. [JDBC](README-JDBC.md)\n" +
                "3. [JavaEE](README-JAVAEE.md)\n" +
                "4. [Spring](README-SPRING.md)\n" +
                "5. [Spring-MVC](README-SPRING-MVC.md)\n" +
                "6. [Spring-Data](README-SPRING-DATA.md)\n";

        File file = new File("io-utf8.txt");
        if (!file.exists()) {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s);
            fileWriter.flush();
            fileWriter.close();
        }

        file = new File("io-gbk.txt");
        if (!file.exists()) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("GBK"));
            outputStreamWriter.write(s);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
    }

    @Test
    public void test1() {
        RandomAccessFile randomAccessFile1 = null;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile1 = new RandomAccessFile(new File("io-utf8.txt"), "r");
            randomAccessFile2 = new RandomAccessFile(new File("io-utf8-2.txt"), "rw");

            byte[] b = new byte[20];
            int len;
            while ((len = randomAccessFile1.read(b)) != -1) {
                randomAccessFile2.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (randomAccessFile1 != null) {
                try {
                    randomAccessFile1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(new File("io-utf8-2.txt"), "rw");
            randomAccessFile.seek(3L);
            randomAccessFile.write("xy".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test3() {
        RandomAccessFile randomAccessFile = null;
        try {
            int start = 5;
            randomAccessFile = new RandomAccessFile(new File("io-utf8-2.txt"), "rw");
            randomAccessFile.seek(start);

            // 先读出来
            byte[] b = new byte[20];
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = randomAccessFile.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            randomAccessFile.seek(start);
            randomAccessFile.write("xy".getBytes());
            randomAccessFile.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
