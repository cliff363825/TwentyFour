package io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestNIO {
    @Test
    public void testPath() {
        Path path = Paths.get("io-utf8.txt");

        // Path toAbsolutePath(): 作为绝对路径返回调用Path对象
        Path absolutePath = path.toAbsolutePath();
        System.out.println("path.toAbsolutePath() = " + absolutePath);

        // boolean startsWith(String path): 判断是否以path路径开始
        // absolutePath.startsWith("Users") = true
        System.out.println("absolutePath.startsWith(\"Users\") = " + absolutePath.startsWith("/Users"));
        // absolutePath.startsWith("User") = false
        System.out.println("absolutePath.startsWith(\"User\") = " + absolutePath.startsWith("/User"));

        // boolean endsWith(String path): 判断是否以path路径结束
        System.out.println("absolutePath.endsWith(\"io-utf8.txt\") = " + absolutePath.endsWith("io-utf8.txt"));

        // boolean isAbsolute(): 判断是否是绝对路径
        System.out.println("path.isAbsolute() = " + path.isAbsolute());
        System.out.println("absolutePath.isAbsolute() = " + absolutePath.isAbsolute());

        // Path getParent(): 返回Path对象包含整个路径，不包含Path对象指定的文件路径
        System.out.println("absolutePath.getParent() = " + absolutePath.getParent());

        // Path getRoot(): 返回调用Path对象的跟路径
        System.out.println("absolutePath.getRoot() = " + absolutePath.getRoot());

        // Path getFileName(): 返回与调用Path对象关联的文件名
        System.out.println("absolutePath.getFileName() = " + absolutePath.getFileName());

        // int getNameCount(): 返回Path根目录后面元素的数量
        System.out.println("absolutePath.getNameCount() = " + absolutePath.getNameCount());

        // Path getName(int idx): 返回指定索引位置idx的路径名称
        System.out.println("absolutePath.getName(1) = " + absolutePath.getName(1));

        // File toFile(): 将Path转化为File类的对象
        System.out.println("absolutePath.toFile() = " + absolutePath.toFile());
    }

    @Test
    public void testFiles() throws IOException {
        Path path = Paths.get("io-utf8-2.txt");
        if (!Files.exists(path)) {
            Files.copy(Paths.get("io-utf8.txt"), Paths.get("io-utf8-2.txt"));
        }
        System.out.println("Files.isRegularFile(path) = " + Files.isRegularFile(path));

        Path tmp1 = Paths.get("tmp1");
        if (!Files.exists(tmp1)) {
            Files.createDirectory(tmp1);
        }
        System.out.println("Files.isDirectory(tmp1) = " + Files.isDirectory(tmp1));
    }
}
