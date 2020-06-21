package io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class TestFileInputOutputStream {
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
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(s.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        }

        file = new File("io-gbk.txt");
        if (!file.exists()) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(s.getBytes(Charset.forName("GBK")));
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

    @Test
    public void testFileInputStream1() {
        // 1. 创建一个File类对象
        File file = new File("io-utf8.txt");
        // 2. 创建一个FileInputStream类的对象
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            // 3. 调用FileInputStream的方法，实现file文件的读取
            // read(): 读取文件的一个字节。当执行到文件结尾时，返回-1
            int b;
            while ((b = fileInputStream.read()) != -1) {
                System.out.print((char) b); // 乱码
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭相应的流
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileInputStream2() {
        File file = new File("io-utf8.txt");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] b = new byte[5]; // 读取到的数据要写入的数组。
            int len; // 每次读入到byte中的字节的长度
            while ((len = fileInputStream.read(b)) != -1) {
                String s = new String(b, 0, len);
                System.out.print(s); // 乱码
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileOutputStream1() {
        // 1. 创建一个File对象，表名要写入的文件位置
        // 输出的物理文件可以不存在，当执行过程中，若不存在，会自动的创建。若存在，会将原有的文件覆盖
        File file = new File("io-utf8.txt");
        // 2. 创建一个FileOutputStream的对象，将file的对象作为形参传递给FileOutputStream的构造器中
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file, true);
            // 3. 写入的操作
            fileOutputStream.write("Hello,中国\n".getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                // 4. 关闭输出流
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileInputOutputStream1() {
        // 1. 提供读入、写出的文件
        File file1 = new File("io-utf8.txt");
        File file2 = new File("io-utf8-2.txt");
        // 2. 提供相应的流
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file1);
            fileOutputStream = new FileOutputStream(file2);
            // 3. 实现文件的复制
            byte[] b = new byte[20];
            int len;
            while ((len = fileInputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
                fileOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileInputOutputStream2() {
        // 1. 提供读入、写出的文件
        File file1 = new File("travel.jpg");
        File file2 = new File("travel-2.jpg");
        // 2. 提供相应的流
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file1);
            fileOutputStream = new FileOutputStream(file2);
            // 3. 实现文件的复制
            byte[] b = new byte[1024];
            int len;
            while ((len = fileInputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
                fileOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testCopyFile() throws Exception {
        long start = System.currentTimeMillis();
        copyFile("travel.jpg", "travel-3.jpg");
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));
    }

    // 实现文件复制
    public static void copyFile(String src, String dest) throws IOException {
        // 1. 提供读入、写出的文件
        File file1 = new File(src);
        File file2 = new File(dest);
        // 2. 提供相应的流
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file1);
            fileOutputStream = new FileOutputStream(file2);
            // 3. 实现文件的复制
            byte[] b = new byte[1024];
            int len;
            while ((len = fileInputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
                fileOutputStream.flush();
            }
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }
}
