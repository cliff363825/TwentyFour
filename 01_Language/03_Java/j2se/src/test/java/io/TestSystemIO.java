package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestSystemIO {
    // 标准输入、输出流
    // 标准输出流： System.out
    // 标准输入流： System.in
    public static void main(String[] args) {
        InputStream in = System.in;
        // 把标准输入流（键盘输入）这个节点流包装成字符流，在包装成缓冲流
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("请输入字符串：");
        String str;
        try {
            while ((str = bufferedReader.readLine()) != null) { // 读取用户输入的一行数据 ----> 阻塞程序
                if (str.equalsIgnoreCase("e") || str.equalsIgnoreCase("exit")) {
                    break;
                }
                // 将读取到的整行字符串转成大写输出
                String str1 = str.toUpperCase();
                System.out.println(str1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close(); // 关闭过滤流时，会自动关闭它所包装的底层字节流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
