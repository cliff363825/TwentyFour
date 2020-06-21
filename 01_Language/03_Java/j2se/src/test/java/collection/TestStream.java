package collection;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStream {
    @Test
    public void test01() {
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(s -> System.out.println(s)); // a1
    }

    @Test
    public void test02() {
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(s -> System.out.println(s)); // a1
    }

    @Test
    public void test03() {
        IntStream.range(1, 4)
                .forEach(value -> System.out.println(value));
        // 1
        // 2
        // 3
    }

    @Test
    public void test04() {
        Arrays.stream(new int[]{1, 2, 3})
                .map(operand -> operand * 2 + 1)
                .average()
                .ifPresent(value -> System.out.println(value)); // 5.0
    }

    @Test
    public void test05() {
        IntStream.range(1, 4)
                .mapToObj(value -> "a" + value)
                .forEach(s -> System.out.println(s));
        // a1
        // a2
        // a3
    }

    @Test
    public void test06() {
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(value -> value.intValue())
                .mapToObj(value -> "a" + value)
                .forEach(s -> System.out.println(s));
        // a1
        // a2
        // a3
    }

    @Test
    public void test07() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
        // filter: d2
        // forEach: d2
        // filter: a2
        // forEach: a2
        // filter: b1
        // forEach: b1
        // filter: b3
        // forEach: b3
        // filter: c
        // forEach: c
    }

    @Test
    public void test08() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
        // map: d2
        // anyMatch: D2
        // map: a2
        // anyMatch: A2
    }

    @Test
    public void test09() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));
        // map: d2
        // filter: D2
        // map: a2
        // filter: A2
        // forEach: A2
        // map: b1
        // filter: B1
        // map: b3
        // filter: B3
        // map: c
        // filter: C
    }

    @Test
    public void test10() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
        // filter: d2
        // filter: a2
        // map: a2
        // forEach: A2
        // filter: b1
        // filter: b3
        // filter: c
    }

    @Test
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

    @Test
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

    @Test
    public void test13() {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(x -> true);
        streamSupplier.get().noneMatch(x -> true);
    }

    private static List<Person> people = Arrays.asList(
            new Person("Max", 18),
            new Person("Peter", 23),
            new Person("Pamela", 23),
            new Person("David", 12)
    );

    @Test
    public void test14() {
        List<Person> p1 = people.stream()
                .filter(person -> person.getName().startsWith("P"))
                .collect(Collectors.toList());
        System.out.println(p1); // [Person{name='Peter', age=23}, Person{name='Pamela', age=23}]
    }

    @Test
    public void test15() {
        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(person -> person.getAge()));
        System.out.println(peopleByAge);
        // {18=[Person{name='Max', age=18}], 23=[Person{name='Peter', age=23}, Person{name='Pamela', age=23}], 12=[Person{name='David', age=12}]}
    }

    @Test
    public void test16() {
        Double averageAge = people.stream()
                .collect(Collectors.averagingInt(value -> value.getAge()));
        System.out.println(averageAge); // 19.0
    }

    @Test
    public void test17() {
        IntSummaryStatistics ageSummary = people.stream()
                .collect(Collectors.summarizingInt(value -> value.getAge()));
        System.out.println(ageSummary);
        // IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
    }

    @Test
    public void test18() {
        String phrase = people.stream()
                .filter(person -> person.getAge() >= 18)
                .map(person -> person.getName())
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
        System.out.println(phrase);
        // In Germany Max and Peter and Pamela are of legal age.
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void test22() {
        people.stream()
                .reduce((person, person2) -> person.getAge() > person2.getAge() ? person : person2)
                .ifPresent(person -> System.out.println(person)); // Person{name='Pamela', age=23}
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void test26() {
        // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        System.out.println(forkJoinPool.getParallelism()); // 3
    }

    @Test
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

    @Test
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

    @Test
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
