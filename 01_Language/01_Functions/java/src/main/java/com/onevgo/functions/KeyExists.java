package com.onevgo.functions;

import java.util.Map;

public class KeyExists {
    public static <K, V> boolean keyExists(K key, Map<K, V> map) {
        return map.containsKey(key);
    }
}
