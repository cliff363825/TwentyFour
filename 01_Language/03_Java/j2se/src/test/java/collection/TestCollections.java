package collection;

import org.junit.Test;

import java.util.*;

public class TestCollections {
    @Test
    @SuppressWarnings("unchecked")
    public void test1() {
        List list = new ArrayList();
        list.add("AAA");
        list.add("BBB");
        list.add(123);
        list.add(456);

        System.out.println("list = " + list);
        // reverse(List): 反转List中元素的顺序
        Collections.reverse(list);
        System.out.println("list = " + list);
        // shuffle(List): 对List几个元素进行随机排序
        Collections.shuffle(list);
        System.out.println("list = " + list);
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
        System.out.println("list = " + list);
        Collections.swap(list, 0, 1);
        System.out.println("list = " + list);
    }

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(123);
        list.add(789);
        list.add(456);
        list.add(123);
        list.add(456);
        System.out.println("Collections.max(coll) = " + Collections.max(list));
        System.out.println("Collections.min(coll) = " + Collections.min(list));
        System.out.println("Collections.frequency(coll, 123) = " + Collections.frequency(list, 123));

        List<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[list.size()]));
        Collections.copy(list1, list);
        System.out.println("list1 = " + list1);

        Collections.replaceAll(list, 123, 321);
        System.out.println("list = " + list);
    }
}
