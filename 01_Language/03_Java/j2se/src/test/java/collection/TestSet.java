package collection;

import org.junit.Test;

import java.util.*;

public class TestSet {
    @Test
    @SuppressWarnings("unchecked")
    public void testHashSet() {
        Set set = new HashSet();
        set.add(123);
        set.add(456);
        set.add("AA");
        set.add("BB");
        set.add(null);
        System.out.println("set = " + set);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLinkedHashSet() {
        Set set = new LinkedHashSet();
        set.add(123);
        set.add(456);
        set.add(new String("AA"));
        set.add(new String("AA"));
        set.add("BB");
        set.add(null);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // 123 456 AA BB null
        }
        System.out.println();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testTreeSet1() {
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
            System.out.print(str + " "); // AA GG JJ MM
        }
        System.out.println();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testTreeSet2() {
        // 定制排序
        // 1. 创建一个实现了Comparator接口的类对象
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 == o2) {
                    return 0;
                }

                Customer c1 = (Customer) o1;
                Customer c2 = (Customer) o2;

                if (c1 != null && c2 != null) {
                    if (c1.getId() != null && c2.getId() != null) {
                        int i = c1.getId().compareTo(c2.getId());
                        if (i != 0) {
                            return i;
                        }

                        if (c1.getName() != null && c2.getName() != null) {
                            return c1.getName().compareTo(c2.getName());
                        }
                        if (c1.getName() != null) {
                            return 1;
                        }
                        if (c2.getName() != null) {
                            return -1;
                        }

                        return 0;
                    }
                    if (c1.getId() != null) {
                        return 1;
                    }
                    if (c2.getId() != null) {
                        return -1;
                    }

                    return 0;
                }
                if (c1 != null) {
                    return 1;
                }
                if (c2 != null) {
                    return -1;
                }

                return 0;
            }
        };

        // 2. 将此对象作为形参传递给TreeSet的构造器中
        TreeSet set = new TreeSet(com);

        // 3. 向TreeSet中添加Comparator接口中的compare方法中涉及的类的对象。
        set.add(new Customer("AA", 1003));
        set.add(new Customer("BB", 1002));
        set.add(new Customer("GG", 1004));
        set.add(new Customer("CC", 1001));
        set.add(new Customer("DD", 1007));
        set.add(new Customer("DD", null));
        set.add(new Customer(null, 1007));
        set.add(new Customer(null, null));

        // Customer{id=null, name='DD'} Customer{id=1001, name='CC'} Customer{id=1002, name='BB'}
        // Customer{id=1003, name='AA'} Customer{id=1004, name='GG'} Customer{id=1007, name='null'}
        // Customer{id=1007, name='DD'}
        for (Object c : set) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
