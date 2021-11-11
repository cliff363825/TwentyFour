package com.onevgo.j2se.generic;

public class ExampleSuper<T1, T2> {
}

// 子类不保留父类的泛型
// 1. 没有类型 擦除
// 相当于 class ExampleA extends ExampleSuper<Object, Object>
class ExampleA extends ExampleSuper {

}

// 2. 具体类型
class ExampleB extends ExampleSuper<Integer, String> {

}

// 子类保留父类的泛型
// 1. 全部保留
class ExampleC<T1, T2> extends ExampleSuper<T1, T2> {

}

// 2. 部分保留
class ExampleD<T2> extends ExampleSuper<Integer, T2> {

}

// 子类不保留父类的泛型
// 1. 没有类型 擦除
class ExampleE<A, B> extends ExampleSuper {

}

// 2. 具体类型
class ExampleF<A, B> extends ExampleSuper<Integer, String> {

}

// 子类保留父类的泛型
// 1. 全部保留
class ExampleG<T1, T2, A, B> extends ExampleSuper<T1, T2> {

}

// 2. 部分保留
class ExampleH<T2, A, B> extends ExampleSuper<Integer, T2> {

}