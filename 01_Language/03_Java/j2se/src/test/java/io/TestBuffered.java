package io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class TestBuffered {
    @Test
    public void testBufferedOutputStream() {
        String s = "# 好脑子不如烂笔头系列之java\n" +
                "===========================\n" +
                "1. [JavaSE](README-JAVASE.md)\n" +
                "2. [JDBC](README-JDBC.md)\n" +
                "3. [JavaEE](README-JAVAEE.md)\n" +
                "4. [Spring](README-SPRING.md)\n" +
                "5. [Spring-MVC](README-SPRING-MVC.md)\n" +
                "6. [Spring-Data](README-SPRING-DATA.md)\n";

        File file = new File("io-utf8.txt");
        BufferedOutputStream bufferedOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(s.getBytes(Charset.forName("UTF-8")));
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testBufferedInputOutputStream() {
        // 1. 提供读入、写出文件
        File file1 = new File("travel.jpg");
        File file2 = new File("travel2.jpg");
        // 2. 创建相应的节点流，FileInputStream FileOutputStream
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file1);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            // 3. 将创建的节点流的对象作为形参传递给缓冲流的构造器中
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            // 4. 具体的实现文件复制的操作
            byte[] b = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(b)) != -1) {
                bufferedOutputStream.write(b, 0, len);
                // 4.1 刷出缓冲区
                bufferedOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭相应的流
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testCopyFile() throws Exception {
        long start = System.currentTimeMillis();
//        File file1 = new File("travel.jpg");
        copyFile("travel.jpg", "travel2.jpg");
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start));
    }

    public static void copyFile(String src, String dest) throws IOException {
        // 1. 提供读入、写出文件
        File file1 = new File(src);
        File file2 = new File(dest);
        // 2. 创建相应的节点流，FileInputStream FileOutputStream
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file1);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            // 3. 将创建的节点流的对象作为形参传递给缓冲流的构造器中
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            // 4. 具体的实现文件复制的操作
            byte[] b = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(b)) != -1) {
                bufferedOutputStream.write(b, 0, len);
                // 4.1 刷出缓冲区
                bufferedOutputStream.flush();
            }
        } finally {
            // 5. 关闭相应的流
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        }
    }

    @Test
    public void testBufferedWriter() {
        String s = "# 好脑子不如烂笔头系列之java\n" +
                "===========================\n" +
                "1. [JavaSE](README-JAVASE.md)\n" +
                "2. [JDBC](README-JDBC.md)\n" +
                "3. [JavaEE](README-JAVAEE.md)\n" +
                "4. [Spring](README-SPRING.md)\n" +
                "5. [Spring-MVC](README-SPRING-MVC.md)\n" +
                "6. [Spring-Data](README-SPRING-DATA.md)\n";

        File file = new File("io-utf8.txt");
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(s);
            bufferedWriter.flush();
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
        }
    }

    @Test
    public void testBufferedReader() {
        File file = new File("io-utf8.txt");
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
//            char[] c = new char[1024];
//            int len;
//            while ((len = br.read(c)) != -1) {
//                System.out.println(new String(c, 0, len));
//            }
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testBufferedReaderWriter() {
        File file1 = new File("io-utf8.txt");
        File file2 = new File("io-utf8-2.txt");

        // 1. 创建缓冲流对象： 它是过滤流，是对节点流的包装
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            FileReader fileReader = new FileReader(file1);
            FileWriter fileWriter = new FileWriter(file2);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            char[] c = new char[1024];
            int len;
            while ((len = bufferedReader.read(c)) != -1) {
                bufferedWriter.write(c, 0, len);
                // 2. 刷新缓冲区
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 3. 关闭IO流对象
            if (bufferedWriter != null) {
                // 关闭过滤流时，会自动关闭它所包装的底层节点流
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
