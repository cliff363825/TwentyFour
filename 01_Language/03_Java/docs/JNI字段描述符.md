# [JNI字段描述符](https://www.cnblogs.com/fnlingnzb-learner/p/7241118.html)

### `([Ljava/lang/String;)V` 它是一种对函数返回值和参数的编码。这种编码叫做JNI字段描述符（JavaNative Interface FieldDescriptors)。一个数组int[]，就需要表示为这样`[I`。如果多个数组double[][][]就需要表示为这样 `[[[D`。也就是说每一个方括号开始，就表示一个数组维数。多个方框后面，就是数组 的类型。

如果以一个L开头的描述符，就是类描述符，它后紧跟着类的字符串，然后分号`;`结束。

比如`Ljava/lang/String;`就是表示类型String；

`[I`就是表示int[];

`[Ljava/lang/Object;`就是表示Object[]。

JNI方法描述符，主要就是在括号里放置参数，在括号后面放置返回类型，如下：

（参数描述符）返回类型

当一个函数不需要返回参数类型时，就使用`V`来表示。

比如`()Ljava/lang/String;`就是表示String f();

`(ILjava/lang/Class;)J`就是表示long f(int i, Class c);

`([B)V`就是表示void String(byte[] bytes); 

| Java 类型  | 符号                                                         |
| ---------- | ------------------------------------------------------------ |
| Boolean    | Z                                                            |
| Byte       | B                                                            |
| Char       | C                                                            |
| Short      | S                                                            |
| Int        | I                                                            |
| Long       | J                                                            |
| Float      | F                                                            |
| Double     | D                                                            |
| Void       | V                                                            |
| Object对象 | 以`L`开头，以`;`结尾，中间是用`/` 隔开的包及类名。比如：`Ljava/lang/String;`如果是嵌套类，则用`$`来表示嵌套。例如 `(Ljava/lang/String;Landroid/os/FileUtils$FileStatus;)Z` |

另外数组类型的简写,则用`[`加上如表A所示的对应类型的简写形式进行表示就可以了，

比如：`[I` 表示 int[]，`[L/java/lang/objects;`表示Objects[]，另外。引用类型（除基本类型的数组外）的标示最后都有个`;`

例如：

`()V` 就表示void Func();

`(II)V` 表示 void Func(int, int);

`(Ljava/lang/String;Ljava/lang/String;)I`表示 int Func(String,String)

