package jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class TestWeakHashMap {
    public static void main(String[] args) {
//        myHashMap();
        myWeakHashMap();
    }

    public static void myHashMap() {
        Map<Integer, String> map = new HashMap<>();

        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);
    }

    public static void myWeakHashMap() {
        Map<Integer, String> map = new WeakHashMap<>();

        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);
    }
}
