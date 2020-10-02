package com.onevgo.functions;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class HashUpdateStream {
    public static int hashUpdateStream(MessageDigest context, InputStream handle) {
        int result = 0;
        try {
            byte[] b = new byte[8192];
            int len = -1;
            while ((len = handle.read(b)) != -1) {
                context.update(b, 0, len);
                result += len;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
