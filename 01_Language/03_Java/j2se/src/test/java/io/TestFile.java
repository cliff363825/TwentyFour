package io;

import cn.hutool.core.io.FileUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

public class TestFile {
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
        // public File(String pathname):
        // 以pathname为路径创建File对象，可以是绝对路径或者相对路径，
        // 如果pathname是相对路径，则默认的当前路径在系统属性user.dir中存储。
        // 1. 绝对路径：是一个固定的路径，从盘符开始
        // 2. 相对路径：是相对于某个位置开始
        File file = new File("io-utf8.txt");

        // public String getAbsolutePath(): 获取绝对路径
        System.out.println("file1.getAbsolutePath() = " + file.getAbsolutePath());

        // public String getPath(): 获取路径
        System.out.println("file1.getPath() = " + file.getPath());

        // public String getName(): 获取名称
        System.out.println("file1.getName() = " + file.getName());

        // public String getParent(): 获取上层文件目录路径，若无，返回null
        System.out.println("file1.getParent() = " + file.getParent());

        // public long length(): 获取文件长度（字节数）。不能获取目录长度
        System.out.println("file1.length() = " + file.length());

        // public long lastModified(): 获取最后一次的修改时间，毫秒值
        System.out.println("file1.lastModified() = " + file.lastModified());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());
        System.out.println("file1.lastModifiedDateTime = " + localDateTime);

        // public String[] list(): 获取指定目录下的所有文件或者文件目录的名称数组
        File parentFile = file.getAbsoluteFile().getParentFile();
        System.out.println("parentFile.list() = " + Arrays.toString(parentFile.list()));

        // public File[] listFiles(): 获取指定目录下的所有文件或者文件目录的File数组
        System.out.println("parentFile.listFiles() = " + Arrays.toString(parentFile.listFiles()));
        List<File> files = FileUtil.loopFiles(parentFile);
        System.out.println("files = " + files);
    }

    @Test
    public void test2() {
        File file = new File("io-utf8.txt");

        // public boolean isDirectory(): 判断是否是文件目录
        System.out.println("file.isDirectory() = " + file.isDirectory());

        // public boolean isFile(): 判断是否是文件
        System.out.println("file.isFile() = " + file.isFile());

        // public boolean exists(): 判断是否存在
        System.out.println("file.exists() = " + file.exists());

        // public boolean canRead(): 判断是否可读
        System.out.println("file.canRead() = " + file.canRead());

        // public boolean canWrite(): 判断是否可写
        System.out.println("file.canWrite() = " + file.canWrite());

        // public boolean isHidden(): 判断是否隐藏
        System.out.println("file.isHidden() = " + file.isHidden());

        File file1 = new File("io-utf8-2.txt");
        // public boolean renameTo(File dest): 把文件重命名为指定的文件路径
        System.out.println("file.renameTo(file1) = " + file.renameTo(file1));

        System.out.println("file.isDirectory() = " + file.isDirectory());
        System.out.println("file.isFile() = " + file.isFile());
        System.out.println("file.exists() = " + file.exists());
        System.out.println("file.canRead() = " + file.canRead());
        System.out.println("file.canWrite() = " + file.canWrite());
        System.out.println("file.isHidden() = " + file.isHidden());

        FileUtil.move(file1, file, false);
    }

    @Test
    public void test3() throws Exception {
        File file = new File("io-utf8.txt");

        // public boolean createNewFile(): 创建文件。若文件存在，则不创建，返回false
        System.out.println("file.createNewFile() = " + file.createNewFile());

        // public boolean delete(): 删除文件或者文件夹
        System.out.println("file.delete() = " + file.delete());
        System.out.println(FileUtil.del(file));
    }

    @Test
    public void test4() throws Exception {
        File dir1 = new File("tmp1");
        if (!dir1.exists()) { // 如果目录不存在，就创建为目录
            System.out.println("dir1.mkdir() = " + dir1.mkdir());
        }

        // 创建以dir1为父目录，名为dir2的File对象
        File dir2 = new File(dir1, "tmp2/tmp3");
        if (!dir2.exists()) { // 如果还不存在，就创建目录
            System.out.println("dir2.mkdirs() = " + dir2.mkdirs());
        }

        // 创建以dir2为父目录。名为test.txt的File对象
        File file = new File(dir2, "tmp4.txt");
        if (!file.exists()) { // 如果还不存在，就创建为文件
            System.out.println("file.createNewFile() = " + file.createNewFile());
        }

        file = new File(dir1, "tmp5.txt");
        file.createNewFile();
        file = new File(dir1, "tmp6.txt");
        file.createNewFile();

        System.out.println("dir1.list() = " + Arrays.toString(dir1.list()));
        System.out.println("dir1.listFiles(.txt) = " + Arrays.toString(dir1.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (new File(dir, name).isDirectory()) {
                    return true;
                }

                if (name.endsWith(".txt")) {
                    return true;
                }

                return false;
            }
        })));

        System.out.println("dir1.delete() = " + dir1.delete());
        System.out.println(FileUtil.del(dir1));
        System.out.println("dir1.exists() = " + dir1.exists());
    }
}
