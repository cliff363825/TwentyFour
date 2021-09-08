package com.onevgo.j2se.collection;


import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class CollectionsMain {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("AAA");
        list.add("BBB");
        list.add(123);
        list.add(456);

        log.info("{}", list);

        // reverse(List): 反转List中元素的顺序
        Collections.reverse(list);
        log.info("reverse = {}", list);

        // shuffle(List): 对List几个元素进行随机排序
        Collections.shuffle(list);
        log.info("shuffle = {}", list);

        // sort(List): 根据元素的自然顺序对指定List几个元素按升序排序
        // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
//        Collections.sort(list);
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 == o2) {
                    return 0;
                }

                return String.valueOf(o1).compareTo(String.valueOf(o2));
            }
        });
        log.info("sort = {}", list);

        Collections.swap(list, 0, 1);
        log.info("swap = {}", list);

        list.clear();
        list.add(123);
        list.add(789);
        list.add(456);
        list.add(123);
        list.add(456);

        // max
        log.info("max = {}", Collections.max(list));
        // min
        log.info("min = {}", Collections.min(list));
        // frequency
        log.info("frequency = {}", Collections.frequency(list, 123));
        // copy
        List dest = Arrays.asList(new Integer[list.size()]);
        Collections.copy(dest, list);
        log.info("copy = {}", dest);
        // replaceAll
        Collections.replaceAll(list, 123, 321);
        log.info("replaceAll = {}", list);
    }
}
