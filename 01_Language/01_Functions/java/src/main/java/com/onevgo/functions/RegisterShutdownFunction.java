package com.onevgo.functions;

public class RegisterShutdownFunction {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Script executed with success");
            }
        });
    }
}
