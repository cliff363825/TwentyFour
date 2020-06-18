package com.onevgo.functions;

import java.util.Collection;
import java.util.Map;

public class Count {
    public static int count(Collection coll) {
        return coll == null ? 0 : coll.size();
    }

    public static int count(Map map) {
        return map == null ? 0 : map.size();
    }

    public static void main(String[] args) {

    }
}
