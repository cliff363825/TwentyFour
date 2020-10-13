package com.onevgo.functions;

public class Chr {
    public static byte chr(int bytevalue) {
        return (byte) bytevalue;
    }

    public static void main(String[] args) {
        System.out.printf("The string ends in escape: %c", chr(27));
        System.out.println();

        String s1 = new String(new byte[]{chr(240), chr(159), chr(144), chr(152)});
        System.out.println(s1);
    }
}
