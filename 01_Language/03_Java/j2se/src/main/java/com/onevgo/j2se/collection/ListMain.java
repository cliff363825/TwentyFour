package com.onevgo.j2se.collection;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ListMain {
    public static void main(String[] args) {
//        testArrayList();
//        testLinkedList();
//        testArrayToList();
//        testArrayToListStream();
        testListToArray();
    }

    private static void testArrayList() {
        List list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(new String("AA"));
        list.add(new String("GG"));
        log.info("list = {}", list);

        // void add(int index, Object ele): 在index位置插入ele元素
        list.add(0, 555);
        log.info("add(0,555) = {}", list);

        // void addAll(int index, Collection eles): 从index位置开始将eles中的所有元素添加进来
        list.addAll(1, Arrays.asList("BB", "CC"));
        log.info("addAll(1,[BB,CC]) = {}", list);

        // Object get(int index): 获取指定index位置的元素
        log.info("get(0) = {}", list.get(0));

        // Object remove(int index): 移除指定index位置的元素，并返回此元素
        log.info("remove(0) = {}", list.remove(0));

        // Object set(int index, Object ele): 设置指定index位置的元素为ele
        log.info("set(0,996) = {}", list.set(0, 999));

        list.add(456);

        // int indexOf(Object obj): 返回obj在集合中首次出现的位置
        log.info("indexOf(456) = {}", list.indexOf(456));
        // int lastIndexOf(Object obj): 返回obj在当前几个中末次出现的位置
        log.info("lastIndexOf(456) = {}", list.lastIndexOf(456));

        // List subList(int fromIndex, int toIndex): 返回从fromIndex到toIndex位置的子集合
        log.info("subList(0,3) = {}", list.subList(0, 3));
    }

    private static void testLinkedList() {
        // addFirst(Object obj)
        LinkedList list = new LinkedList();
        list.addFirst("AA");
        list.addFirst("BB");
        list.addFirst("CC");
        log.info("{}", list);

        // addLast(Object obj)
        list.addLast(11);
        log.info("addList(11) = {}", list);

        // Object getFirst()
        log.info("getFirst() = {}", list.getFirst());
        // Object getLast()
        log.info("getLast() = {}", list.getLast());
        // Object removeFirst()
        log.info("removeFirst() = {}", list.removeFirst());
        // Object removeLast()
        log.info("removeLast() = {}", list.removeLast());
        log.info("{}", list);
    }

    private static void testArrayToList() {
        Integer[] integers = new Integer[]{1, 2, 3};

        // 对象数组转List
        List<Integer> integerList = Arrays.asList(integers);
        log.info("integerList.getClass() = {}", integerList.getClass());
//        integerList.add(4); // java.lang.UnsupportedOperationException

        integerList = new ArrayList<>(integerList);
        log.info("integerList.getClass() = {}", integerList.getClass());
        integerList.add(4);

        log.info("{}", integerList);
    }

    private static void testArrayToListStream() {
        int[] ints = new int[]{1, 2, 3};

        // 基础数据类型数组转List，自动装箱
        List<Integer> integerList = Arrays.stream(ints).boxed().collect(Collectors.toList());
        log.info("{}", integerList);

        integerList.add(4);
        log.info("{}", integerList);
    }

    private static void testListToArray() {
        List<String> stringList = new ArrayList<>();
        stringList.add("aa");
        stringList.add("bb");
        stringList.add("cc");
        stringList.add("dd");
        stringList.add("ee");

        String[] strings = stringList.toArray(new String[0]);
        log.info("{}", Arrays.toString(strings));
    }
}
