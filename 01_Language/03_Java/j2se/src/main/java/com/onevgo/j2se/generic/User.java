package com.onevgo.j2se.generic;

// 编译不通过，非泛型类继承泛型类必须指定泛型类型
//public class User extends Person<T> {
//}

public class User extends Example<User> {
}
