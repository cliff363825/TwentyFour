package com.onevgo.j2se.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class SetMain {
    public static void main(String[] args) {
//        testHashSet();
//        testLinkedHashSet();
        testTreeSet3();
    }

    private static void testHashSet() {
        Set set = new HashSet();
        set.add(123);
        set.add(456);
        set.add("AA");
        set.add("BB");
        set.add(null);
        log.info("{}", set);
    }

    private static void testLinkedHashSet() {
        Set set = new LinkedHashSet();
        set.add(123);
        set.add(456);
        set.add(new String("AA"));
        set.add(new String("AA"));
        set.add("BB");
        set.add(null);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            log.info("{}", iterator.next());
        }
    }

    private static void testTreeSet1() {
        // 自然排序
        Set set = new TreeSet();
//        set.add(123);
//        set.add(456);
        set.add(new String("AA"));
        set.add(new String("AA"));
        set.add("JJ");
        set.add("GG");
        set.add("MM");
        for (Object str : set) {
            log.info("{}", str);
        }
    }

    private static void testTreeSet3() {
        TreeSet<Customer> set = new TreeSet<>(
                Comparator.nullsFirst(Comparator.comparing(Customer::getId, Comparator.<Integer>nullsFirst(null).thenComparing(Integer::compareTo)))
                        .thenComparing(Customer::getName, Comparator.<String>nullsFirst(null).thenComparing(String::compareTo))
        );
        set.add(new Customer("AA", 1003));
        set.add(new Customer("BB", 1002));
        set.add(new Customer("GG", 1004));
        set.add(new Customer("CC", 1001));
        set.add(new Customer("DD", 1007));
        set.add(new Customer("DD", null));
        set.add(new Customer(null, 1007));
        set.add(new Customer(null, null));
        for (Object c : set) {
            log.info("{}", c);
        }
    }

    static class Customer {
        private Integer id;
        private String name;

        public Customer(String name, Integer id) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
