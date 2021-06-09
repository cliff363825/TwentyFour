package com.onevgo.j2se.annotation;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExampleAnnotation("Repeatable")
@ExampleAnnotation("TYPE")
@Slf4j
public class ExampleObject<@ExampleAnnotation("TYPE_PARAMETER") T> {
    @ExampleAnnotation("FIELD")
    private T name;

    @ExampleAnnotation("METHOD")
    public T getName() {
        return name;
    }

    public void setName(@ExampleAnnotation("PARAMETER") T name) {
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
        log.info("name = {}", name);
    }

    // @SuppressWarnings：抑制编译器警告
    @SuppressWarnings("unchecked")
    public boolean addElement(List list, Object object) {
        long size = (@ExampleAnnotation("TYPE_USE") long) list.size();
        return list.add(object);
    }

    @ExampleAnnotation(value = "showAnnotation", items = {@ExampleItem("item1"), @ExampleItem("item2")})
    public void showAnnotation() {
        @ExampleAnnotation("LOCAL_VARIABLE") Annotation[] classAnnotations = getClass().getAnnotations();
        log.info("class annotations = {}", Arrays.stream(classAnnotations).collect(Collectors.toList()));

        try {
            Annotation[] methodAnnotations = getClass().getMethod("showAnnotation").getAnnotations();
            log.info("method annotations = {}", Arrays.stream(methodAnnotations).collect(Collectors.toList()));
        } catch (NoSuchMethodException e) {
            log.error("exception = ", e);
        }
    }
}
