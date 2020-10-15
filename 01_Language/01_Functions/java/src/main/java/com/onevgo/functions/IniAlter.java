package com.onevgo.functions;

public class IniAlter {
    public static String iniAlter(String varname, String newvalue) {
        return System.setProperty(varname, newvalue);
    }

    public static void main(String[] args) {
        System.out.println(iniAlter("display_errors", "1"));
    }
}
