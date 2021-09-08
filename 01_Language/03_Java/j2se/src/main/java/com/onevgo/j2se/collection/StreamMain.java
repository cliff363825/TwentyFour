package com.onevgo.j2se.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class StreamMain {
    private static List<Person> people = Arrays.asList(
            new Person("Max", 18),
            new Person("Peter", 23),
            new Person("Pamela", 23),
            new Person("David", 12)
    );

    public static void main(String[] args) {
//        testStream();
//        testIntStream();
//        testArrayToStream();
//        testIntStreamMapToObj();
//        testStreamMapToInt();
//        testFilter();
//        testAnyMatch();
        testMapFilter();
    }

    private static void testStream() {
        Arrays.asList("a1", "a2", "a3").stream().findFirst().ifPresent(s -> log.info("{}", s));
        Stream.of("a1", "a2", "a3").findFirst().ifPresent(s -> log.info("{}", s));
    }

    private static void testIntStream() {
        IntStream.range(1, 4).forEach(i -> log.info("{}", i));
    }

    private static void testArrayToStream() {
        int[] ints = {1, 2, 3};
        Arrays.stream(ints).map(i -> i * 2 + 1).average().ifPresent(d -> log.info("{}", d));
    }

    private static void testIntStreamMapToObj() {
        IntStream.range(1, 4).mapToObj(i -> "a" + i).forEach(s -> log.info("{}", s));
    }

    private static void testStreamMapToInt() {
        Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "a" + i).forEach(s -> log.info("{}", s));
    }

    private static void testFilter() {
        Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
            log.info("filter: {}", s);
            return true;
        }).forEach(s -> log.info("forEach: {}", s));
    }

    private static void testAnyMatch() {
        Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
            log.info("map: {}", s);
            return s.toUpperCase();
        }).anyMatch(s -> {
            log.info("anyMatch: {}", s);
            return s.startsWith("A");
        });
    }

    private static void testMapFilter() {
        log.info("map -> filter -> forEach -> map -> ...");
        Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
            log.info("map: {}", s);
            return s.toUpperCase();
        }).filter(s -> {
            log.info("filter: {}", s);
            return s.startsWith("A");
        }).forEach(s -> {
            log.info("forEach: {}", s);
        });

        log.info("filter -> map -> forEach -> filter -> ...");
        Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
            log.info("filter: {}", s);
            return s.startsWith("a");
        }).map(s -> {
            log.info("map: {}", s);
            return s.toUpperCase();
        }).forEach(s -> {
            log.info("forEach: {}", s);
        });

        // 能过滤则优先过滤 ！！
    }

    public void test11() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((o1, o2) -> {
                    System.out.printf("sort: %s; %s\n", o1, o2);
                    return o1.compareTo(o2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
        // sort: a2; d2
        // sort: b1; a2
        // sort: b1; d2
        // sort: b1; a2
        // sort: b3; b1
        // sort: b3; d2
        // sort: c; b3
        // sort: c; d2
        // filter: a2
        // map: a2
        // forEach: A2
        // filter: b1
        // filter: b3
        // filter: c
        // filter: d2
    }

    public void test12() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((o1, o2) -> {
                    System.out.printf("sort: %s; %s\n", o1, o2);
                    return o1.compareTo(o2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
        // filter: d2
        // filter: a2
        // filter: b1
        // filter: b3
        // filter: c
        // map: a2
        // forEach: A2
    }

    public void test13() {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(x -> true);
        streamSupplier.get().noneMatch(x -> true);
    }

    public void test14() {
        List<Person> p1 = people.stream()
                .filter(person -> person.getName().startsWith("P"))
                .collect(Collectors.toList());
        System.out.println(p1); // [Person{name='Peter', age=23}, Person{name='Pamela', age=23}]
    }


    public void test15() {
        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(person -> person.getAge()));
        System.out.println(peopleByAge);
        // {18=[Person{name='Max', age=18}], 23=[Person{name='Peter', age=23}, Person{name='Pamela', age=23}], 12=[Person{name='David', age=12}]}
    }


    public void test16() {
        Double averageAge = people.stream()
                .collect(Collectors.averagingInt(value -> value.getAge()));
        System.out.println(averageAge); // 19.0
    }


    public void test17() {
        IntSummaryStatistics ageSummary = people.stream()
                .collect(Collectors.summarizingInt(value -> value.getAge()));
        System.out.println(ageSummary);
        // IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
    }


    public void test18() {
        String phrase = people.stream()
                .filter(person -> person.getAge() >= 18)
                .map(person -> person.getName())
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
        System.out.println(phrase);
        // In Germany Max and Peter and Pamela are of legal age.
    }


    public void test19() {
        Map<Integer, String> map = people.stream()
                .collect(Collectors.toMap(
                        o -> o.getAge(),
                        o -> o.getName(),
                        (o, o2) -> o + ";" + o2)
                );

        System.out.println(map);
        // {18=Max, 23=Peter;Pamela, 12=David}
    }


    public void test20() {
        String names = people.stream()
                .collect(Collector.of(
                        () -> new StringJoiner(" | "),
                        (stringJoiner, o) -> stringJoiner.add(o.getName()),
                        (stringJoiner, stringJoiner2) -> stringJoiner.merge(stringJoiner2),
                        stringJoiner -> stringJoiner.toString())
                );
        System.out.println(names);
        // MAX | PETER | PAMELA | DAVID
    }


    public void test21() {
        ArrayList<Foo> foos = new ArrayList<>();

        IntStream.range(1, 4)
                .forEach(value -> foos.add(new Foo("Foo" + value)));

        foos.forEach(foo ->
                IntStream.range(1, 4)
                        .forEach(value -> foo.getBars().add(new Bar("Bar" + value + " <- " + foo.getName())))
        );

        foos.stream()
                .flatMap(foo -> foo.getBars().stream())
                .forEach(bar -> System.out.println(bar.getName()));
        // Bar1 <- Foo1
        // Bar2 <- Foo1
        // Bar3 <- Foo1
        // Bar1 <- Foo2
        // Bar2 <- Foo2
        // Bar3 <- Foo2
        // Bar1 <- Foo3
        // Bar2 <- Foo3
        // Bar3 <- Foo3
    }


    public void test22() {
        people.stream()
                .reduce((person, person2) -> person.getAge() > person2.getAge() ? person : person2)
                .ifPresent(person -> System.out.println(person)); // Person{name='Pamela', age=23}
    }


    public void test23() {
        Person person = people.stream()
                .reduce(new Person("", 0), (person1, person2) -> {
                    person1.setAge(person1.getAge() + person2.getAge());
                    person1.setName(person1.getName() + "," + person2.getName());
                    return person1;
                });
        System.out.println(person);
        // Person{name='MaxPeterPamelaDavid', age=76}
    }


    public void test24() {
        Integer age = people.stream()
                .reduce(0, (integer, person) -> {
                    System.out.printf("accumulator: sum=%s; person=%s\n", integer, person);
                    return integer + person.getAge();
                }, (integer, integer2) -> {
                    System.out.printf("combiner: sum1=%s; sum2=%s;\n", integer, integer2);
                    return integer + integer2;
                });
        System.out.println(age);
        // accumulator: sum=0; person=Person{name='Max', age=18}
        // accumulator: sum=18; person=Person{name='Peter', age=23}
        // accumulator: sum=41; person=Person{name='Pamela', age=23}
        // accumulator: sum=64; person=Person{name='David', age=12}
    }


    public void test25() {
        Integer age = people.parallelStream()
                .reduce(0, (integer, person) -> {
                    System.out.printf("accumulator: sum=%s; person=%s\n", integer, person);
                    return integer + person.getAge();
                }, (integer, integer2) -> {
                    System.out.printf("combiner: sum1=%s; sum2=%s\n", integer, integer2);
                    return integer + integer2;
                });
        System.out.println(age);
        // accumulator: sum=0; person=Person{name='David', age=12}
        // accumulator: sum=0; person=Person{name='Max', age=18}
        // accumulator: sum=0; person=Person{name='Peter', age=23}
        // combiner: sum1=18; sum2=23
        // accumulator: sum=0; person=Person{name='Pamela', age=23}
        // combiner: sum1=23; sum2=12
        // combiner: sum1=41; sum2=35
        // 76
    }


    public void test26() {
        // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        System.out.println(forkJoinPool.getParallelism()); // 3
    }


    public void test27() {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.printf("filter: %s [%s]\n", s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.printf("map: %s [%s]\n", s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> {
                    System.out.printf("forEach: %s [%s]\n", s, Thread.currentThread().getName());
                });
        // filter: a2 [ForkJoinPool.commonPool-worker-1]
        // filter: a1 [ForkJoinPool.commonPool-worker-2]
        // map: a1 [ForkJoinPool.commonPool-worker-2]
        // map: a2 [ForkJoinPool.commonPool-worker-1]
        // forEach: A2 [ForkJoinPool.commonPool-worker-1]
        // forEach: A1 [ForkJoinPool.commonPool-worker-2]
        // filter: b1 [main]
        // map: b1 [main]
        // forEach: B1 [main]
        // filter: c2 [ForkJoinPool.commonPool-worker-1]
        // map: c2 [ForkJoinPool.commonPool-worker-1]
        // filter: c1 [ForkJoinPool.commonPool-worker-2]
        // map: c1 [ForkJoinPool.commonPool-worker-2]
        // forEach: C1 [ForkJoinPool.commonPool-worker-2]
        // forEach: C2 [ForkJoinPool.commonPool-worker-1]
    }


    public void test28() {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.printf("filter: %s [%s]\n", s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.printf("map: %s [%s]\n", s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((o1, o2) -> {
                    System.out.printf("sort: %s <> %s [%s]\n", o1, o2, Thread.currentThread().getName());
                    return o1.compareTo(o2);
                })
                .forEach(s -> {
                    System.out.printf("forEach: %s [%s]\n", s, Thread.currentThread().getName());
                });
        // filter: a2 [ForkJoinPool.commonPool-worker-1]
        //filter: a1 [ForkJoinPool.commonPool-worker-2]
        //map: a1 [ForkJoinPool.commonPool-worker-2]
        //map: a2 [ForkJoinPool.commonPool-worker-1]
        //filter: c1 [ForkJoinPool.commonPool-worker-2]
        //filter: c2 [ForkJoinPool.commonPool-worker-1]
        //map: c1 [ForkJoinPool.commonPool-worker-2]
        //map: c2 [ForkJoinPool.commonPool-worker-1]
        //filter: b1 [main]
        //map: b1 [main]
        //sort: A2 <> A1 [main]
        //sort: B1 <> A2 [main]
        //sort: C2 <> B1 [main]
        //sort: C1 <> C2 [main]
        //sort: C1 <> B1 [main]
        //sort: C1 <> C2 [main]
        //forEach: C2 [ForkJoinPool.commonPool-worker-2]
        //forEach: B1 [main]
        //forEach: A1 [ForkJoinPool.commonPool-worker-1]
        //forEach: A2 [ForkJoinPool.commonPool-worker-3]
        //forEach: C1 [ForkJoinPool.commonPool-worker-2]
    }


    public void test29() {
        people.parallelStream()
                .reduce(0, (integer, person) -> {
                    System.out.format("accumulator: sum=%s; person=%s [%s]\n",
                            integer, person, Thread.currentThread().getName());
                    return integer + person.getAge();
                }, (integer, integer2) -> {
                    System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
                            integer, integer2, Thread.currentThread().getName());
                    return integer + integer2;
                });
        // accumulator: sum=0; person=Person{name='Peter', age=23} [ForkJoinPool.commonPool-worker-1]
        //accumulator: sum=0; person=Person{name='David', age=12} [ForkJoinPool.commonPool-worker-3]
        //accumulator: sum=0; person=Person{name='Max', age=18} [ForkJoinPool.commonPool-worker-2]
        //combiner: sum1=18; sum2=23 [ForkJoinPool.commonPool-worker-2]
        //accumulator: sum=0; person=Person{name='Pamela', age=23} [main]
        //combiner: sum1=23; sum2=12 [main]
        //combiner: sum1=41; sum2=35 [main]
    }
}
