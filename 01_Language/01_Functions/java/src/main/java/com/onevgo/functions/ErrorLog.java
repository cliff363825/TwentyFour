package com.onevgo.functions;

import java.util.logging.Logger;

public class ErrorLog {
    public static void main(String[] args) {
        Logger.getGlobal().severe("You messed up!");
    }
}
