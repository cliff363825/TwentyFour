package hello;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestEquals {
    @Test
    public void test1() {
        Son son1 = new Son();
        son1.setName("tom");

        Son son2 = new Son();
        son2.setName("smith");

        Son son3 = new Son();
        son3.setName("tom");

        System.out.println("son1==son2?" + (son1 == son2)); // false
        System.out.println("son1.equals(son2)?" + son1.equals(son2)); // false

        System.out.println("son1==son3?" + (son1 == son3)); // false
        System.out.println("son1.equals(son3)?" + son1.equals(son3)); // true
    }

    @Test
    public void test2() {
        Set<Son> sons = new HashSet<>();

        Son son1 = new Son();
        son1.setName("tom");

        Son son2 = new Son();
        son2.setName("smith");

        Son son3 = new Son();
        son3.setName("tom");

        sons.add(son1);
        sons.add(son2);
        sons.add(son3);

        System.out.println("sons.size()=" + sons.size()); // 2
        System.out.println("sons=" + sons);
    }

    @Test
    public void test3() {
        Map<Son, String> sons = new HashMap<>();

        Son son1 = new Son();
        son1.setName("tom");

        Son son2 = new Son();
        son2.setName("smith");

        Son son3 = new Son();
        son3.setName("tom");

        sons.put(son1, son1.getName());
        sons.put(son2, son2.getName());
        sons.put(son3, son3.getName());

        System.out.println("sons.size()=" + sons.size()); // 2
        System.out.println("sons=" + sons);
    }
}
