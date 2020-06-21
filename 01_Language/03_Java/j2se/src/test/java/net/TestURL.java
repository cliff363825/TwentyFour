package net;

import org.junit.Test;

import java.io.*;
import java.net.*;

public class TestURL {
    @Test
    public void test1() throws MalformedURLException {
        URL url = new URL("https://www.onevgo.com");
        System.out.println("url = " + url);

        // 获取该URL的协议名
        System.out.println("url.getProtocol() = " + url.getProtocol());

        // 获取该URL的主机名
        System.out.println("url.getHost() = " + url.getHost());

        // 获取该URL的端口名
        System.out.println("url.getPort() = " + url.getPort());

        // 获取该URL的文件路径
        System.out.println("url.getPath() = " + url.getPath());

        // 获取该URL的文件名
        System.out.println("url.getFile() = " + url.getFile());

        // 获取该URL的查询名
        System.out.println("url.getQuery() = " + url.getQuery());
    }

    @Test
    public void test2() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        File file = new File("url.html");
        try {
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            URL url = new URL("https://www.onevgo.com");
            System.out.println("url = " + url);

            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
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
