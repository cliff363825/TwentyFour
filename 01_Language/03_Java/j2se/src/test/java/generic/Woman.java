package generic;

// 编译不通过，非泛型类继承泛型类必须指定泛型类型
//public class Woman extends Person<T> {
//}

public class Woman extends Person<Integer> {
}
