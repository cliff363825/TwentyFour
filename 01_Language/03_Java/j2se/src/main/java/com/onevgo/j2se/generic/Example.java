package com.onevgo.j2se.generic;

import lombok.ToString;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@ToString
public class Example<T> {
    // 使用T类型定义变量
    private T delegate;

    private Class<T> clazz;

    public Example() {
        // 1. 获取带有泛型的父类 Type
        Type genericSuperclass = getClass().getGenericSuperclass();
        // 2. 判断父类 Type 是不是参数化类型 ParameterizedType
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            // 3. 获取参数化类型 ParameterizedType 的实际类型参数 Type[]
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            // 4. 实际类型参数数组的长度 > 0, 即 <X, Y, Z, ...>
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                clazz = (Class<T>) actualTypeArguments[0];
            }
        }
    }

    // 使用T类型定义构造器
    public Example(T delegate) {
        this();
        this.delegate = delegate;
    }

    // 使用T类型定义一般方法
    public T getDelegate() {
        return delegate;
    }

    public void setDelegate(T delegate) {
        this.delegate = delegate;
    }

    // static的方法中不能使用类的泛型
//    public static void show(T t) {
//    }

    // 不能在try-catch中使用泛型定义
//    public void test() {
//        try {
//        } catch (T ex) {
//        }
//    }

    public Class<T> getClazz() {
        return clazz;
    }

}