package com.onevgo.j2se.base;

@FunctionalInterface
public interface ExampleLambda<T> {
    T getValue(T t);
}
