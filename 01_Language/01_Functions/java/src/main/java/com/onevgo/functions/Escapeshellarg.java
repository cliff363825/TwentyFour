package com.onevgo.functions;

public class Escapeshellarg {
    public static String escapeshellarg(String arg) {
        return "'" + arg.replace("'", "'\\\''") + "'";
    }

    public static void main(String[] args) {
        System.out.println("ls " + escapeshellarg("./"));
        System.out.println("ls " + escapeshellarg("','"));
    }
}
