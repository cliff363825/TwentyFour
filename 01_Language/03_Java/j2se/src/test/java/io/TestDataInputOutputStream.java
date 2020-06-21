package io;

import org.junit.Test;

import java.io.*;

public class TestDataInputOutputStream {
    // 数据流 DataInputStream DataOutputStream
    @Test
    public void testDataOutputStream() {
        DataOutputStream dataOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("data.txt"));
            // 创建连接到指定文件的数据输出流对象
            dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeUTF("我爱你，而你却不知道！"); // 写UTF字符串
            dataOutputStream.writeBoolean(true); // 写入布尔值
            dataOutputStream.writeLong(1432522344L); // 写入长整数
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流对象
            if (dataOutputStream != null) {
                try {
                    // 关闭过滤流时，会自动关闭它所包装的底层字节流
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testDataInputStream() {
        DataInputStream dataInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("data.txt"));
            dataInputStream = new DataInputStream(fileInputStream);
            String s = dataInputStream.readUTF();
            System.out.println(s);
            boolean b = dataInputStream.readBoolean();
            System.out.println(b);
            long l = dataInputStream.readLong();
            System.out.println(l);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
