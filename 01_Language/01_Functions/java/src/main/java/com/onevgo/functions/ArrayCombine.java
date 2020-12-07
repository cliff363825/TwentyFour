package com.onevgo.functions;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.collection.ListUtil;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;

public class ArrayCombine {
    public static <K, V> Map<K, V> arrayCombine(Iterable<K> keys, Iterable<V> values) {
        return IterUtil.toMap(keys, values, true);
    }

    public static <K, V> Map<K, V> arrayCombine(K[] keys, V[] values) {
        return IterUtil.toMap(ListUtil.of(keys), ListUtil.of(values), true);
    }

    public static void main(String[] args) {
        List<String> a = ImmutableList.of("green", "red", "yellow");
        List<String> b = ImmutableList.of("avocado", "apple", "banana");
        Map<String, String> map = arrayCombine(a, b);
        System.out.println(map);
    }
}
