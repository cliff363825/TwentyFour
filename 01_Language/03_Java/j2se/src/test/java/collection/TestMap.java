package collection;

import org.junit.Test;

import java.util.*;

public class TestMap {
    @Test
    @SuppressWarnings("unchecked")
    public void testHashMap1() {
        Map map = new HashMap();
        map.put("AA", 213);
        map.put("BB", 456);
        map.put("BB", 456);
        map.put(123, "CC");
        map.put(null, null);
        System.out.println("map = " + map);
        System.out.println("map.size() = " + map.size());
        System.out.println("map.remove(\"BB\") = " + map.remove("BB"));
        System.out.println("map.get(123) = " + map.get(123));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHashMap2() {
        Map map = new HashMap();
        map.put("AA", 213);
        map.put("BB", 456);
        map.put("BB", 456);
        map.put(123, "CC");
        map.put(null, null);

        // 1. 遍历key集
        Set keySet = map.keySet();
        for (Object key : keySet) {
            System.out.print(key + " "); // AA BB null 123
        }
        System.out.println();

        // 2. 遍历value集
        Collection values = map.values();
        Iterator iterator = values.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // 213 456 null CC
        }
        System.out.println();

        // 3. 如何遍历key-value对
        // 方式一：
        for (Object key : keySet) {
            System.out.printf("map[%s]=%s\n", key, map.get(key));
        }

        //  方式二
        Set<Map.Entry> entrySet = map.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.printf("map[%s]=%s\n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLinkHashMap() {
        Map map = new LinkedHashMap();
        map.put("AA", 213);
        map.put("BB", 456);
        map.put("BB", 456);
        map.put(123, "CC");
        map.put(null, null);

        Set entrySet = map.entrySet();
        for (Object obj : entrySet) {
            Map.Entry entry = (Map.Entry) obj;
            System.out.printf("map[%s]=%s\n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testTreeMap1() {
        // 定制排序：Map的key实现Comparable接口
        Map map = new TreeMap();
        map.put(new Person("AA", 23), 89);
        map.put(new Person("MM", 22), 79);
        map.put(new Person("GG", 23), 99);
        map.put(new Person("JJ", 13), 69);

        Set entrySet = map.entrySet();
        for (Object obj : entrySet) {
            Map.Entry entry = (Map.Entry) obj;
            System.out.printf("map[%s]=%s\n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testTreeMap2() {
        // 定制排序：Comparator对象
        Map map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 == o2) {
                    return 0;
                }
                Person p1 = (Person) o1;
                Person p2 = (Person) o2;
                if (p1.getAge() == p2.getAge()) {
                    return p1.getName().compareTo(p2.getName());
                } else {
                    return -(p1.getAge() - p2.getAge());
                }
            }
        });
        map.put(new Person("AA", 23), 89);
        map.put(new Person("MM", 22), 79);
        map.put(new Person("GG", 23), 99);
        map.put(new Person("JJ", 13), 69);

        Set entrySet = map.entrySet();
        for (Object obj : entrySet) {
            Map.Entry entry = (Map.Entry) obj;
            System.out.printf("map[%s]=%s\n", entry.getKey(), entry.getValue());
        }
    }
}
