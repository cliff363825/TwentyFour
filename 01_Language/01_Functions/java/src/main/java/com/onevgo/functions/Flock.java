package com.onevgo.functions;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public class Flock {
    public static void main(String[] args) {
        RandomAccessFile randomAccessFile = null;
        FileLock lock = null;
        try {
            randomAccessFile = new RandomAccessFile(new File("test.txt"), "rw");
            lock = randomAccessFile.getChannel().lock();
//            lock = randomAccessFile.getChannel().tryLock();
            randomAccessFile.setLength(0);
            randomAccessFile.write("hello world".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (lock != null) {
                try {
                    lock.release();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
