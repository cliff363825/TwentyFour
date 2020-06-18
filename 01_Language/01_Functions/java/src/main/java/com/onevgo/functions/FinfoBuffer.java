package com.onevgo.functions;

import net.sf.jmimemagic.*;

import java.io.*;
import java.net.URLConnection;
import java.util.Arrays;

public class FinfoBuffer {
    public static String finfoBuffer(byte[] data) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try {
            return URLConnection.guessContentTypeFromStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String finfoBuffer2(byte[] data) {
        try {
            return Magic.getMagicMatch(data).getMimeType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String finfoBuffer2(byte[] data, boolean onlyMimeMatch) {
        try {
            return Magic.getMagicMatch(data, onlyMimeMatch).getMimeType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("test.gif"));
            byte[] buf = new byte[512];
            int len = inputStream.read(buf);
            byte[] data = Arrays.copyOf(buf, len);
            System.out.println(finfoBuffer(data));
            System.out.println(finfoBuffer2(data));
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
    }
}
