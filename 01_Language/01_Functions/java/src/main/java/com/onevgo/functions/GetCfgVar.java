package com.onevgo.functions;

public class GetCfgVar {
    public static String getCfgVar(String option) {
        return System.getProperty(option);
    }

    public static void main(String[] args) {
        System.out.println(getCfgVar("user.home"));
    }
}
