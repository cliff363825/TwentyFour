package collection;

import org.junit.Test;

import java.util.*;

public class TestCollection {
    @Test
    @SuppressWarnings("unchecked")
    public void testCollection1() {
        Collection collection = new ArrayList();
        // 添加
        // add(Object obj)
        // addAll(Collection coll)
        collection.add(123);
        collection.add("AA");
        collection.add(new Date());
        collection.add("BB");

        // 获取有效元素的个数
        // int size()
        System.out.println("collection.size() = " + collection.size());

        Collection collection1 = Arrays.asList(1, 2, 3);
        collection.addAll(collection1);
        System.out.println("collection1 = " + collection1);

        // 是否是空集合
        System.out.println("collection.isEmpty() = " + collection.isEmpty());

        // 清空集合
        collection.clear();
        System.out.println("collection.isEmpty() = " + collection.isEmpty());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCollection2() {
        Collection collection = new ArrayList();
        collection.add(123);
        collection.add("AA");
        collection.add(new Date());
        collection.add("BB");

        // 是否包含某个元素
        // boolean contains(Object obj): 是通过元素的equals方法来判断是否是同一个对象
        // boolean containsAll(Collection c): 也是调用元素的equals方法来比较的。那两个集合的元素挨个比较。
        System.out.println("collection.contains(123) = " + collection.contains(123));
        System.out.println("collection.contains(\"AA\") = " + collection.contains(new String("AA")));

        Collection collection1 = new ArrayList();
        collection1.add(123);
        collection1.add(new String("AA"));
        System.out.println("collection.containsAll(collection1) = " + collection.containsAll(collection1));
        collection1.add(456);
        System.out.println("collection.containsAll(collection1) = " + collection.containsAll(collection1));

        // 取两个集合的交集
        // boolean retainAll(Collection c): 把交集的结果存在当前集合中，不影响c
        collection.retainAll(collection1);
        System.out.println("collection = " + collection);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCollection3() {
        Collection collection = new ArrayList();
        collection.add(123);
        collection.add("AA");
        collection.add(new Date());
        collection.add("BB");
        collection.add("CC");

        // 删除
        // boolean remove(Object obj): 通过元素的equals方法判断是否是要删除的那个元素。只会删除找到的第一个元素
        // boolean removeAll(Collection coll): 取当前集合的差集
        collection.remove(new String("CC"));
        System.out.println("collection = " + collection);

        Collection collection1 = new ArrayList();
        collection1.add(123);
        collection1.add(new String("AA"));
        collection.removeAll(collection1);
        System.out.println("collection = " + collection);

        // 集合是否相等
        // boolean equals(Object obj)
        Collection collection2 = new ArrayList();
        collection2.add("AA");
        collection2.add(123);
        // collection1.equals(collection2) = false
        System.out.println("collection1.equals(collection2) = " + collection1.equals(collection2));

        // 获取集合对象的哈希值
        // hashCode()
        System.out.println("collection.hashCode() = " + collection.hashCode());

        // 转成对象数组
        // Object[] toArray()
        System.out.println("collection.toArray() = " + Arrays.toString(collection.toArray()));

        // 遍历
        // iterator()：返回迭代器对象，用于集合遍历
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println("collection = " + collection);
    }
}
