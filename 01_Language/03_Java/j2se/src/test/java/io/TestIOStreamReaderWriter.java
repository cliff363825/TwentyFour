package io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class TestIOStreamReaderWriter {
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

    // 转换流： InputStreamReader OutputStreamWriter
    // 编码：字符串 -> 字节数组
    // 解码：字节数组 -> 字符串
    @Test
    public void testIOStreamReaderWriter() {
        File file = new File("io-gbk.txt");
        File file1 = new File("io-gbk-utf8.txt");
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("GBK"));
            bufferedReader = new BufferedReader(inputStreamReader);

            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, Charset.forName("UTF-8"));
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
