package io;

import org.junit.Test;

import java.io.*;

public class TestObjectInputOutputStream {
    @Test
    public void testObjectOutputStream() {
        // 序列化:将对象写入到磁盘或者进行网络传输。
        // 要求对象必须实现序列化
        Person person1 = new Person("小米", 23, new Pet("花花"));
        Person person2 = new Person("红米", 21, new Pet("小花"));

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("person.txt"));
            objectOutputStream.writeObject(person1);
            objectOutputStream.flush();
            objectOutputStream.writeObject(person2);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testObjectInputStream() {
        ObjectInputStream objectInputStream = null;
        try {
            // 反序列化：将磁盘中的对象数据源读出。
            objectInputStream = new ObjectInputStream(new FileInputStream("person.txt"));
            Person p1 = (Person) objectInputStream.readObject();
            Person p2 = (Person) objectInputStream.readObject();
            System.out.println(p1);
            System.out.println(p2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
