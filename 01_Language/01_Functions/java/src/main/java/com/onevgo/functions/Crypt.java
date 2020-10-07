package com.onevgo.functions;

import com.onevgo.commons.BCrypt;
import org.apache.commons.codec.digest.Md5Crypt;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crypt {
    public static String crypt(String str) {
        return crypt(str, null);
    }

    public static String crypt(String str, String salt) {
        if (salt == null) {
            return Md5Crypt.md5Crypt(str.getBytes(StandardCharsets.UTF_8));
        }

        Pattern pattern = Pattern.compile("^\\$2[abxy]?\\$");
        Matcher matcher = pattern.matcher(salt);
        if (matcher.find()) {
            return BCrypt.hashpw(str, salt);
        }

        return org.apache.commons.codec.digest.Crypt.crypt(str, salt);
    }

    public static void main(String[] args) {
        String hashedPassword = crypt("mypassword");
        System.out.println(hashedPassword);
        if (HashEquals.hashEquals(hashedPassword, crypt("123456", hashedPassword))) {
            System.out.println("password=123456");
        }
        if (HashEquals.hashEquals(hashedPassword, crypt("mypassword", hashedPassword))) {
            System.out.println("password=mypassword");
        }

        System.out.println("Standard DES: " + crypt("rasmuslerdorf", "rl"));
//        System.out.println("Extended DES: " + crypt("rasmuslerdorf", "_J9..rasm"));
        System.out.println("MD5:          " + crypt("rasmuslerdorf", "$1$rasmusle$"));
        System.out.println("Blowfish:     " + crypt("rasmuslerdorf", "$2a$07$usesomesillystringforsalt$"));
        System.out.println("SHA-256:      " + crypt("rasmuslerdorf", "$5$rounds=5000$usesomesillystringforsalt$"));
        System.out.println("SHA-512:      " + crypt("rasmuslerdorf", "$6$rounds=5000$usesomesillystringforsalt$"));
    }
}
