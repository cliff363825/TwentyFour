package generic;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class TestGeneric {
    @Test
    @SuppressWarnings("unchecked")
    public void test1() {
        // 1. 在集合中没有使用泛型的情况下
        List list = new ArrayList();
        list.add(89);
        list.add(87);
        list.add(67);
        // 1. 没有使用泛型，任何Object及其子类的对象都可以添加进来
        list.add(new String("AA"));

        for (int i = 0; i < list.size(); i++) {
            // 2. 强转为int型时，可能报ClassCastException的异常
            int score = (int) list.get(i); // java.lang.ClassCastException
            System.out.println(score);
        }
    }

    @Test
    public void test2() {
        // 2. 在集合中使用泛型
        List<Integer> list = new ArrayList<>();
        list.add(89);
        list.add(87);
        list.add(67);
//        list.add("AA"); // 编译不通过
//        for (int i:list) {
//            System.out.println(i);
//        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3() {
        Map<String, Integer> map = new HashMap<>();
        map.put("AA", 78);
        map.put("BB", 87);
        map.put("DD", 98);
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        for (Map.Entry<String, Integer> entry : set) {
            System.out.printf("map[%s]=%s\n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void test4() {
        ArrayList list = new ArrayList();
//        list.add(new Date()); // 有风险
        list.add("hello");
        showList(list); // 泛型擦除，编译不会类型检查

//        ArrayList<Object> list1 = new ArrayList<>();
//        showList(list1); // 一旦指定Object，编译会类型检查，必须按照Object处理
    }

    private void showList(List<String> list) {
        System.out.println("list = " + list);
    }

    @Test
    public void test5() {
        int[] ints = new int[10];
        Collection<Integer> coll = new ArrayList<Integer>();
//        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        fromArrayToCollection(Arrays.stream(ints).boxed().toArray(Integer[]::new), coll);
        System.out.println("coll = " + coll);

        String[] strings = new String[10];
        Collection<String> coll1 = new ArrayList<>();
        fromArrayToCollection(strings, coll1);
        System.out.println("coll1 = " + coll1);
    }

    private <E> void fromArrayToCollection(E[] arr, Collection<E> coll) {
        for (E e : arr) {
            coll.add(e);
        }
    }

    @Test
    public void test6() {
        Person[] people = new Person[10];
        Man[] men = new Man[10];
        // 而Person[] 是 Man[] 的父类
        people = men;

        Person p = men[0];

        // 在泛型的集合上
        List<Person> personList = null;
        List<Man> manList = null;
//        personList = manList; // 报错

        printCollection1(personList);
        printCollection2(personList);

        printCollection1(manList);
//        printCollection2(manList); // 报错
    }

    private void printCollection1(List<? extends Person> list) {
    }

    private void printCollection2(List<? super Person> list) {
    }

    @Test
    public void test7() {
        List<?> list = null;
        list = new ArrayList<String>();
        list = new ArrayList<Double>();
//        list.add(3); // 编译不通过
        list.add(null);

        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add("中国");
        list2.add(15);
        read(list1);
        read(list2);
    }

    private void read(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void test9() throws ClassNotFoundException {
        Class clazz1 = Class.forName("generic.Man");
        Class clazz2 = Class.forName("generic.Woman");
        Class clazz3 = Class.forName("generic.Person");

        Type genType1 = clazz1.getGenericSuperclass();
        Type genType2 = clazz2.getGenericSuperclass();
        Type genType3 = clazz3.getGenericSuperclass();

        System.out.println(genType1); // generic.Person<T>
        System.out.println(genType2); // generic.Person<java.lang.Integer>
        System.out.println(genType3); // class java.lang.Object

        ParameterizedType pt1 = (ParameterizedType) genType1;
        ParameterizedType pt2 = (ParameterizedType) genType2;
        // ParameterizedType pt3 = (ParameterizedType) genType3; // java.lang.ClassCastException: java.lang.Class cannot be cast to java.lang.reflect.ParameterizedType

        System.out.println(Arrays.toString(pt1.getActualTypeArguments())); // [T]
        System.out.println(Arrays.toString(pt2.getActualTypeArguments())); // [class java.lang.Integer]
    }

    @Test
    public void test10() {
        Woman woman = new Woman();
        woman.showGeneric();
    }
}
