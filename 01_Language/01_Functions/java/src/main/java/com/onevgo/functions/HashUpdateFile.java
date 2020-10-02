package com.onevgo.functions;

import java.io.*;
import java.security.MessageDigest;

public class HashUpdateFile {
    public static boolean hashUpdateFile(MessageDigest context, String filename) {
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(new File(filename)));
            byte[] b = new byte[8192];
            int len = -1;
            while ((len = inputStream.read(b)) != -1) {
                context.update(b, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
