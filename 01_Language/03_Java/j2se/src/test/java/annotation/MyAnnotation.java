package annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

// @Retention：只能用于修饰一个 Annotation 定义，用于指定该 Annotation 的生命周期，@Retention 包含一个 RetentionPolicy 类型的成员变量，使用 @Retention 时必须为该 value 成员变量指定值
// RetentionPolicy.SOURCE：在源文件中有效（即源文件保留），编译器直接丢弃这种策略的注释
//@Retention(value = RetentionPolicy.SOURCE)

// RetentionPolicy.CLASS：在class文件中有效（即class保留），当运行 Java 程序时，JVM 不会保留注解。这是默认值
//@Retention(value = RetentionPolicy.CLASS)

// RetentionPolicy.RUNTIME：在运行时有效（即运行时保留），当运行 Java 程序时，JVM 会保留注释。程序可以通过反射获取该注释
@Retention(value = RetentionPolicy.RUNTIME)

// @Target：用于修饰 Annotation 定义，用于指定被修饰的 Annotation 能用于修饰哪些程序元素。@Target 也包含一个名为 value 的成员变量。
@Target(value = {
        TYPE, // 用于描述类、接口（包括注解类型）或enum声明
        FIELD, // 用于描述域
        METHOD, // 用于描述方法
        PARAMETER, // 用于描述参数
        CONSTRUCTOR, // 用于描述构造器
        LOCAL_VARIABLE, // 用于描述局部变量
        ANNOTATION_TYPE, // 用于描述注解类型
        PACKAGE, // 用于描述包
        TYPE_PARAMETER, // jdk1.8，表示该注解能写在类型变量的声明语句中（如：泛型声明）
        TYPE_USE // jdk1.8，表示该注解能写在使用类型的任何语句中。
})

// @Documented：用于指定被该元 Annotation 修饰的 Annotation 类将被 javadoc 工具提取成文档。默认情况下，javadoc是不包括注解的。
@Documented

// @Inherited：被它修饰的 Annotation 将具有继承性。如果某个类使用了被 @Inherited 修饰的 Annotation，则其子类将自动具有该注解。
//@Inherited

@Repeatable(MyAnnotation.List.class)
public @interface MyAnnotation {
    String value();

    String name() default "MyAnnotation";

    MyItem[] items() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        MyAnnotation[] value();
    }
}
