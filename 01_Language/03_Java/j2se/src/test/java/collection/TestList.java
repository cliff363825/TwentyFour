package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TestList {
    @Test
    @SuppressWarnings("unchecked")
    public void testArrayList1() {
        List list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(new String("AA"));
        list.add(new String("GG"));
        System.out.println("list = " + list); // list = [123, 456, AA, GG]

        // void add(int index, Object ele): 在index位置插入ele元素
        list.add(0, 555);
        System.out.println("list = " + list); // list = [555, 123, 456, AA, GG]

        // void addAll(int index, Collection eles): 从index位置开始将eles中的所有元素添加进来
        list.addAll(1, Arrays.asList("BB", "CC"));
        System.out.println("list = " + list); // list = [555, BB, CC, 123, 456, AA, GG]

        // Object get(int index): 获取指定index位置的元素
        System.out.println("list.get(0) = " + list.get(0));

        // Object remove(int index): 移除指定index位置的元素，并返回此元素
        System.out.println("list.remove(0) = " + list.remove(0));

        // Object set(int index, Object ele): 设置指定index位置的元素为ele
        System.out.println("list.set(0, 996) = " + list.set(0, 996));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testArrayList2() {
        List list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(new String("AA"));
        list.add(new String("GG"));
        list.add(456);
        System.out.println("list = " + list);

        // int indexOf(Object obj): 返回obj在集合中首次出现的位置
        System.out.println("list.indexOf(456) = " + list.indexOf(456));
        // int lastIndexOf(Object obj): 返回obj在当前几个中末次出现的位置
        System.out.println("list.lastIndexOf(456) = " + list.lastIndexOf(456));
        System.out.println("list.indexOf(123) == list.lastIndexOf(123) = " + (list.indexOf(123) == list.lastIndexOf(123)));
        System.out.println("list.indexOf(444) = " + list.indexOf(444));

        // List subList(int fromIndex, int toIndex): 返回从fromIndex到toIndex位置的子集合
        System.out.println("list.subList(0, 3) = " + list.subList(0, 3));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLinkedList() {
        // addFirst(Object obj)
        LinkedList list = new LinkedList();
        list.addFirst("AA");
        list.addFirst("BB");
        list.addFirst("CC");
        System.out.println("list = " + list);

        // addLast(Object obj)
        list.addLast(11);
        System.out.println("list = " + list);

        // Object getFirst()
        System.out.println("list.getFirst() = " + list.getFirst());
        // Object getLast()
        System.out.println("list.getLast() = " + list.getLast());
        // Object removeFirst()
        System.out.println("list.removeFirst() = " + list.removeFirst());
        // Object removeLast()
        System.out.println("list.removeLast() = " + list.removeLast());
        System.out.println("list = " + list);
    }

    @Test
    public void testArrayToList1() {
        Integer[] integers = new Integer[]{1, 2, 3};

        // 对象数组转List
        List<Integer> integerList = Arrays.asList(integers);
        System.out.println("integerList.getClass()=" + integerList.getClass()); // integerList.getClass()=class java.util.Arrays$ArrayList
//        integerList.add(4); // java.lang.UnsupportedOperationException

        integerList = new ArrayList<>(integerList);
        System.out.println("integerList.getClass()=" + integerList.getClass()); // integerList.getClass()=class java.util.ArrayList
        integerList.add(4);

        System.out.println("integerList=" + integerList);
    }

    @Test
    public void testArrayToList2() {
        int[] ints = new int[]{1, 2, 3};

        // 基础数据类型数组转List，自动装箱
        List<Integer> integerList = Arrays.stream(ints)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("integerList=" + integerList);

        integerList.add(4);
        System.out.println("integerList=" + integerList);
    }

    @Test
    public void testListToArray() {
        List<String> stringList = new ArrayList<>();
        stringList.add("aa");
        stringList.add("bb");
        stringList.add("cc");
        stringList.add("dd");
        stringList.add("ee");

        String[] strings = stringList.toArray(new String[0]);
        System.out.println("strings=" + Arrays.toString(strings)); // strings=[aa, bb, cc, dd, ee]
    }
}
