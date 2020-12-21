package com.onevgo.functions;

import java.util.Collection;

public class Empty {
    public static boolean empty(Collection<?> var) {
        return var == null || var.size() == 0;
    }
}
