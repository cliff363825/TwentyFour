package com.onevgo.j2se.generic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.*;

@Slf4j
public class GenericMain {
    public static void main(String[] args) {
//        testNoGenericCollection();
//        testGenericCollection();
//        testGenericErasure();
//        testInherit();
//        testWildcard();
//        testReflection(null);
        testSuperGenericClass();
    }

    @SuppressWarnings("unchecked")
    private static void testNoGenericCollection() {
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
            log.info("{}", score);
        }
    }

    private static void testGenericCollection() {
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
            log.info("{}", iterator.next());
        }
    }

    private static void testGenericMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("AA", 78);
        map.put("BB", 87);
        map.put("DD", 98);
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        for (Map.Entry<String, Integer> entry : set) {
            log.info("{}={}", entry.getKey(), entry.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    private static void testGenericErasure() {
        List list = new ArrayList();
        list.add(new Date()); // 有风险
        list.add("hello");
        printList(list); // 泛型擦除，编译不会类型检查

//        List<Object> list1 = new ArrayList<>();
//        printList(list1); // 一旦指定Object，编译会类型检查，必须按照Object处理
    }

    private static void printList(List<String> list) {
        System.out.println("list=" + list);
    }

    private static void testArrayToCollection() {
        int[] intArr = new int[10];
        // 1. Arrays.stream()
        Integer[] integers = Arrays.stream(intArr).boxed().toArray(Integer[]::new);

        // 2. IntStream.of() == Arrays.stream()
//        Integer[] integers = IntStream.of(intArr).boxed().toArray(Integer[]::new);
    }

    private static void testInherit() {
        Example[] examples = new Example[10];
        Post[] posts = new Post[10];
        // 而 Example[] 是 Post[] 的父类
        examples = posts;
        log.info("{}", Example[].class.isAssignableFrom(Post[].class));

        // 在泛型的集合上
        List<Example> examples1 = null;
        List<Post> posts1 = null;
//        examples1 = posts1; // 报错

        printCollection1(examples1);
        printCollection2(examples1);

        printCollection1(posts1);
//        printCollection2(posts1); // 报错
    }

    private static void printCollection1(List<? extends Example> list) {
    }

    private static void printCollection2(List<? super Example> list) {
    }

    private static void testWildcard() {
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

    private static void read(List<?> list) {
        for (Object o : list) {
            log.info("{}", o);
        }
    }

    private static <T extends User> void testReflection(List<? super Post> posts) {
        Type genericSuperclass = User.class.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            log.info("{}", Arrays.toString(actualTypeArguments));
        }

        try {
            Method method = GenericMain.class.getDeclaredMethod("testReflection", List.class);
            TypeVariable<Method>[] typeParameters = method.getTypeParameters();
            if (typeParameters.length > 0) {
                for (TypeVariable<Method> typeParameter : typeParameters) {
                    Type[] bounds = typeParameter.getBounds();
                    log.info("{}", Arrays.toString(bounds));
                }
            }

            Type[] genericParameterTypes = method.getGenericParameterTypes();
            if (genericParameterTypes.length > 0) {
                for (Type genericParameterType : genericParameterTypes) {
                    if (genericParameterType instanceof ParameterizedType) {
                        Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                        if (actualTypeArguments.length > 0) {
                            Type actualTypeArgument = actualTypeArguments[0];
                            if (actualTypeArgument instanceof WildcardType) {
                                log.info("{} {}", ((WildcardType) actualTypeArgument).getLowerBounds(), ((WildcardType) actualTypeArgument).getUpperBounds());
                            }
                        }
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            log.error("error=", e);
        }
    }

    private static void testSuperGenericClass() {
        User user = new User();
        log.info("{}", user.getClazz());
    }
}
