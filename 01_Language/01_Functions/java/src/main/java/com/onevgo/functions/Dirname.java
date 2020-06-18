package com.onevgo.functions;

import java.io.File;

public class Dirname {
    public static void main(String[] args) {
        System.out.println(new File("/etc/password").getParent());
        System.out.println(new File("/etc/").getParent());
        System.out.println(new File(".").getParent());
        System.out.println(new File("C:\\").getParent());
        System.out.println(new File("/usr/local/lib").getParent());
    }
}
