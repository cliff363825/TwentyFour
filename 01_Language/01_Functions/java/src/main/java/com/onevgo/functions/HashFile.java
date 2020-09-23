package com.onevgo.functions;

import cn.hutool.core.util.HexUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFile {
    public static String hashFile(String algo, String filename) {
        InputStream inputStream = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(algo);
            inputStream = new FileInputStream(new File(filename));
            byte[] b = new byte[8192];
            int len;
            while ((len = inputStream.read(b)) != -1) {
                digest.update(b, 0, len);
            }
            return HexUtil.encodeHexStr(digest.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
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
        return null;
    }

    public static void main(String[] args) {
        FilePutContents.filePutContents("test.txt", "The quick brown fox jumped over the lazy dog.");
        System.out.println(hashFile("md5", "test.txt"));
    }
}
