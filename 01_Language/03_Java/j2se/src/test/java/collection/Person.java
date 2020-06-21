package collection;

public class Person implements Comparable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }

        if (o == null) {
            return 1;
        }

        Person p = (Person) o;

        if (this.name != null && p.name != null) {
            return this.name.compareTo(p.name);
        }
        if (this.name != null) {
            return 1;
        }
        if (p.getName() != null) {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
