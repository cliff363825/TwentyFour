package com.onevgo.functions;

import cn.hutool.crypto.digest.BCrypt;

public class Crypt {
    public static String crypt(String str) {
        return BCrypt.hashpw(str);
    }

    public static String crypt(String str, String salt) {
        return BCrypt.hashpw(str, salt);
    }

    public static void main(String[] args) {
        String hashedPassword = crypt("mypassword");
        System.out.println(hashedPassword);
        if (BCrypt.checkpw("123456", hashedPassword)) {
            System.out.println("password=123456");
        }
        if (BCrypt.checkpw("mypassword", hashedPassword)) {
            System.out.println("password=mypassword");
        }

//        System.out.println("Standard DES: " + crypt("rasmuslerdorf", "rl"));
//        System.out.println("Extended DES: " + crypt("rasmuslerdorf", "_J9..rasm"));
//        System.out.println("MD5:          " + crypt("rasmuslerdorf", "$1$rasmusle$"));
        System.out.println("Blowfish:     " + crypt("rasmuslerdorf", "$2a$07$usesomesillystringforsalt$"));
//        System.out.println("SHA-256:      " + crypt("rasmuslerdorf", "$5$rounds=5000$usesomesillystringforsalt$"));
//        System.out.println("SHA-512:      " + crypt("rasmuslerdorf", "$6$rounds=5000$usesomesillystringforsalt$"));
    }
}
