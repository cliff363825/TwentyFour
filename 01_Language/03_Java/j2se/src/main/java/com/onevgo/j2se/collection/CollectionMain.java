package com.onevgo.j2se.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class CollectionMain {
    public static void main(String[] args) {
        testCollection();
    }

    private static void testCollection() {
        Collection collection = new ArrayList();
        // 添加
        // add(Object obj)
        // addAll(Collection coll)
        collection.add(1);
        collection.add("A");
        collection.add(new Date());
        collection.add("B");

        // 获取有效元素的个数
        // int size()
        log.info("size = {}", collection.size());

        collection.addAll(Arrays.asList(2, 3, 4));
        log.info("{}", collection);

        // 是否是空集合
        log.info("isEmpty = {}", collection.isEmpty());

        Collection collectionTemp = new ArrayList(collection);
        // 清空集合
        collection.clear();
        log.info("isEmpty = {}", collection.isEmpty());

        collection = collectionTemp;
        // 是否包含某个元素
        // boolean contains(Object obj): 是通过元素的equals方法来判断是否是同一个对象
        // boolean containsAll(Collection c): 也是调用元素的equals方法来比较的。那两个集合的元素挨个比较。
        log.info("contains(1) = {}", collection.contains(1));
        log.info("containsAll(1,2,3) = {}", collection.containsAll(Arrays.asList(1, 2, 3)));

        // 取两个集合的交集
        // boolean retainAll(Collection c): 把交集的结果存在当前集合中，不影响c
        collection.retainAll(Arrays.asList(1, 2, 3, 4));
        log.info("retainAll(1,2,3,4) = {}", collection);

        // 删除
        // boolean remove(Object obj): 通过元素的equals方法判断是否是要删除的那个元素。只会删除找到的第一个元素
        // boolean removeAll(Collection coll): 取当前集合的差集
        collection.remove("1");
        log.info("{}", collection);

        collection.removeAll(Arrays.asList(2, 3));
        log.info("{}", collection);

        // 集合是否相等
        // boolean equals(Object obj)
        log.info("{}", collection.equals(Arrays.asList(4)));

        // 获取集合对象的哈希值
        // hashCode()
        log.info("{}", collection.hashCode());

        // 转成对象数组
        // Object[] toArray()
        log.info("toArray = {}", Arrays.toString(collection.toArray()));

        // 遍历
        // iterator()：返回迭代器对象，用于集合遍历
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            log.info("next = {}", o);
            iterator.remove();
        }
    }
}
