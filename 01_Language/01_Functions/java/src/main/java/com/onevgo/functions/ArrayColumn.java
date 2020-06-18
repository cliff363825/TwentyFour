package com.onevgo.functions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayColumn {

    public static <E, R> List<R> arrayColumn(Collection<E> col, Function<E, R> columnFunc) {
        return col.stream().map(columnFunc).collect(Collectors.toList());
    }

    public static <E, K, V> Map<K, V> arrayColumn(Collection<E> col, Function<E, V> columnFunc, Function<E, K> indexFunc) {
        return col.stream().collect(Collectors.toMap(indexFunc, columnFunc, (v, v2) -> v2, LinkedHashMap::new));
    }

    public static void main(String[] args) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("id", 2135);
        map1.put("first_name", "John");
        map1.put("last_name", "Doe");
        list.add(map1);

        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("id", 3245);
        map2.put("first_name", "Sally");
        map2.put("last_name", "Smith");
        list.add(map2);

        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("id", 5342);
        map3.put("first_name", "Jane");
        map3.put("last_name", "Jones");
        list.add(map3);

        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("id", 5623);
        map4.put("first_name", "Peter");
        map4.put("last_name", "Doe");
        list.add(map4);

        System.out.println(arrayColumn(list, e -> e.get("first_name")));
        System.out.println(arrayColumn(list, e -> e.get("first_name"), e -> e.get("id")));
        System.out.println(arrayColumn(list, e -> e, e -> e.get("id")));

        Person p1 = new Person(2135, "John", "Doe");
        Person p2 = new Person(3245, "Sally", "Smith");
        Person p3 = new Person(5342, "Jane", "Jones");
        Person p4 = new Person(5623, "Peter", "Doe");
        ArrayList<Person> people = new ArrayList<>();
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        System.out.println(arrayColumn(people, Person::getFirstName));
    }

    static class Person {
        int id;
        String firstName;
        String lastName;

        public Person(int id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
