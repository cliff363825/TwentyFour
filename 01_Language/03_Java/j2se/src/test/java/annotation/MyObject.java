package annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

@MyAnnotation("Repeatable")
@MyAnnotation("TYPE")
public class MyObject<@MyAnnotation("TYPE_PARAMETER") T> {
    @MyAnnotation("FIELD")
    private T name;

    @MyAnnotation("METHOD")
    public T getName() {
        return name;
    }

    public void setName(@MyAnnotation("PARAMETER") T name) {
        this.name = name;
    }

    // @Override：限定重写父类方法，该注释只能用于方法
    @Override
    public String toString() {
        return "TestAnnotation{" +
                "name='" + name + '\'' +
                '}';
    }

    // @Deprecated：用于表示某个程序元素（类，方法等）已过时。通常是因为锁修饰的结构危险或存在更好的选择
    @Deprecated
    public void show() {
        System.out.println("name = " + name);
    }

    // @SuppressWarnings：抑制编译器警告
    @SuppressWarnings("unchecked")
    public boolean addElement(List list, Object object) {
        long size = (@MyAnnotation("TYPE_USE") long) list.size();
        return list.add(object);
    }

    @MyAnnotation(value = "showAnnotation", items = {@MyItem("item1"), @MyItem("item2")})
    public void showAnnotation() {
        @MyAnnotation("LOCAL_VARIABLE") Annotation[] classAnnotations = getClass().getAnnotations();
        System.out.println("class annotations=" + Arrays.toString(classAnnotations));

        try {
            Annotation[] methodAnnotations = getClass().getMethod("showAnnotation").getAnnotations();
            System.out.println("method annotations=" + Arrays.toString(methodAnnotations));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
