package com.onevgo.functions;

import java.util.*;

public class ArrayPop {
    public static <E> E arrayPop(List<E> list) {
        if (list.size() == 0) {
            return null;
        }

        return list.remove(list.size() - 1);
    }

    public static <E> E arrayPop(Stack<E> stack) {
        return stack.size() > 0 ? stack.pop() : null;
    }

    public static <K, V> V arrayPop(LinkedHashMap<K, V> map) {
        if (map.size() == 0) {
            return null;
        }

        K lastKey = null;
        for (K k : map.keySet()) {
            lastKey = k;
        }
        return map.remove(lastKey);
    }

    public static void main(String[] args) {
        List<String> stack = new ArrayList<>(Arrays.asList("orange", "banana", "apple", "raspberry"));
        System.out.println(arrayPop(stack));
        System.out.println(stack);

        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("k1", "orange");
        map.put("k2", "banana");
        map.put("k3", "apple");
        map.put("k4", "raspberry");
        System.out.println(arrayPop(map));
        System.out.println(map);
    }
}
