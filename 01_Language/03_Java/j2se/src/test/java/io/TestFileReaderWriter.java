package io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class TestFileReaderWriter {
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
    public void testFileReader() {
        File file = new File("io-utf8.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            char[] c = new char[20];
            int len;
            while ((len = fileReader.read(c)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print(c[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileReader1() {
        File file = new File("io-gbk.txt");
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(file), "gbk");
            char[] chars = new char[20];
            int len;
            while ((len = inputStreamReader.read(chars)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print(chars[i]);
                }
//                System.out.print(new String(chars, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileReaderWriter() {
        // 1. 输入流对应的文件src一定要存在，否则抛异常。输出流对应的文件dest可以不存在，执行过程中会自动创建
        File src = new File("io-utf8.txt");
        File dest = new File("io-utf8-2.txt");
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(src);
            fileWriter = new FileWriter(dest);
            char[] c = new char[20];
            int len;
            while ((len = fileReader.read(c)) != -1) {
                fileWriter.write(c, 0, len);
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileReaderWriter1() {
        // 1. 输入流对应的文件src一定要存在，否则抛异常。输出流对应的文件dest可以不存在，执行过程中会自动创建
        File src = new File("io-gbk.txt");
        File dest = new File("io-gbk-utf8.txt");
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(src), Charset.forName("GBK"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(dest), Charset.forName("UTF-8"));
            char[] c = new char[20];
            int len;
            while ((len = inputStreamReader.read(c)) != -1) {
                outputStreamWriter.write(c, 0, len);
                outputStreamWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
