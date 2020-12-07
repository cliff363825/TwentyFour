package com.onevgo.functions;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.ReflectUtil;

import java.util.*;
import java.util.function.Function;

public class ArrayColumn {
    public static List<Object> arrayColumn(Iterable<?> array, String column) {
        return CollectionUtil.getFieldValues(array, column);
    }

    public static <T> List<T> arrayColumn(Iterable<?> array, String column, Class<T> elementType) {
        return CollectionUtil.getFieldValues(array, column, elementType);
    }

    public static <T, K, V> Map<K, V> arrayColumn(Iterable<T> array, String column, String indexKey) {
        Function<T, V> valueFunc;

        if (column == null) {
            valueFunc = v -> (V) v;
        } else {
            valueFunc = v -> {
                if (v instanceof Map) {
                    return (V) ((Map) v).get(column);
                } else {
                    return (V) ReflectUtil.getFieldValue(v, column);
                }
            };
        }

        Function<T, K> keyFunc = v -> {
            if (v instanceof Map) {
                return (K) ((Map) v).get(indexKey);
            } else {
                return (K) ReflectUtil.getFieldValue(v, indexKey);
            }
        };

        return IterUtil.toMap(new LinkedHashMap<>(), array, keyFunc, valueFunc);
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

        System.out.println(arrayColumn(list, "first_name", String.class));
        System.out.println(arrayColumn(list, "first_name", "id"));
        System.out.println(arrayColumn(list, null, "id"));

        Person p1 = new Person(2135, "John", "Doe");
        Person p2 = new Person(3245, "Sally", "Smith");
        Person p3 = new Person(5342, "Jane", "Jones");
        Person p4 = new Person(5623, "Peter", "Doe");
        ArrayList<Person> people = new ArrayList<>();
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        System.out.println(arrayColumn(people, "firstName"));
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
