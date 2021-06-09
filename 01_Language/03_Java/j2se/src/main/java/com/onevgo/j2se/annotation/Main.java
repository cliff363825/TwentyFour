package com.onevgo.j2se.annotation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ExampleObject<String> exampleObject = new ExampleObject<>();

        List list = new ArrayList();
        exampleObject.addElement(list, 123);
        log.info("list = {}", list);

        // class annotations=[@annotation.MyAnnotation(name=MyAnnotation, items=[], value=MyObject)]
        // method annotations=[@annotation.MyAnnotation(name=MyAnnotation, items=[@annotation.MyItem(value=item1), @annotation.MyItem(value=item2)], value=showAnnotation)]
        exampleObject.showAnnotation();
    }
}
