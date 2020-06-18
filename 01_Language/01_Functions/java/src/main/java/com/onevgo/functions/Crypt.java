package com.onevgo.functions;

import cn.hutool.crypto.digest.BCrypt;

public class Crypt {
    public static String crypt(String s) {
        return BCrypt.hashpw(s);
    }

    public static String crypt(String s, String salt) {
        return BCrypt.hashpw(s, salt);
    }

    public static void main(String[] args) {
        String hashedPassword = crypt("mypassword");
        if (BCrypt.checkpw("123456", hashedPassword)) {
            System.out.println("password=123456");
        }
        if (BCrypt.checkpw("mypassword", hashedPassword)) {
            System.out.println("password=mypassword");
        }
    }
}
