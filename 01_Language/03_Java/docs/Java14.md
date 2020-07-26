# 超实用的Java14新特性

## Java14 新特性概述

## 环境安装

1. JDK14的下载、安装：
   1. https://www.oracle.com/java/technologies/javase-downloads.html
2. IDEA2020.1的下载、安装、配置
   1. https://www.jetbrains.com/idea/nextversion/#section=windows

## 超实用新特性

1. JEP305: instanceof的模式匹配（预览）

   这个特性很有意思，因为它为更为通用的模式匹配打开了大门。模式匹配通过更为简便的语法基于一定的条件来抽取对象的组件，而instanceof刚好是这种情况，他先检查对象类型，然后在调用对象的方法或访问对象的字段。

   有了该功能，可以减少Java程序中显示强制转换的数量，从而提高生产力，还能实现更精确、简洁的类型安全的代码。

   ```java
   if (obj instanceof String) {
     String str = (String)obj;
   	str.contains("...");
   } else {
     //...
   }
   
   //java14
   if (obj instanceof String str) {
     str.contains("...");
   } else {
     ...
   }
   ```

2. JEP358:非常实用的NullPointerException

   1. 该特性改进了NullPointerException的可读性，能更准确的给出null变量的信息。

      该特性可以帮助开发者提高生产力，以及改进各种开发工具和调试工具的质量。一个目标是减少开发人员的困惑和担忧。

      相信很多Java程序员都一样对null和NPE深恶痛绝，因为他确实会带来各种各样的问题（来自《Java8实战》），如：
      1. 它是错误之源。NullPointerException是目前Java程序开发中最典型的异常。它会使你的代码膨胀。
      2. 它让你的代码充斥着深度嵌套的null检查，代码的可读性糟糕透顶。
      3. 它自身是毫无意义的。null自身没有任何的语义，尤其是它代表的是在静态类型语言中以一种错误的方式对缺失变量值的建模。
      4. 它破坏了Java的哲学。Java一直试图避免让程序员意识到指针的存在，唯一的例外是：null指针。
      5. 它在Java的类型系统上开了口子。null并不属于任何类型，这意味着它可以被赋值给任意饮用类型的变量。这会导致问题，原因是当这个变量被传递到系统中的另一个部分后，你将无法获知这个null变量最初赋值到底是什么类型。

   2. 其他语言如何解决NPE问题

      1. 在Groovy中使用安全导航操作符（Safe Navigation Operator）可以访问可能为null的变量：

         ```groovy
         def carInsuranceName = person?.car?.insurance?.name
         ```

      2. 在Haskell和Scala也有类型的替代品，如Haskell中的Maybe类型、Scala中的Option[A]。Option[A]是一个类型为A的可选值的容器

      3. 在Kotlin中，其类型系统严格区分一个引用可以容纳null还是不能容纳。也就是说，一个变量时候可空必须显示声明，对于可空变量，在访问其成员时必须做空处理，否则无法编译通过：

         ```kotlin
         var a: String = "abc"
         a = null // 编译错误
         
         // 如果允许为空，可以声明一个可空字符串，写作 String?
         var b: String? = "abc" // String? 表示该String类型变量可为空
         b = null // 编译通过
         ```

   3. Java做了哪些努力

      1. 首先在Java8中提供了Optional。
         1. Optional在可能为null的对象上做了一层封装，强制你思考值不存在的情况，这样就能避免潜在的空指针异常。
         2. 关于Optional的用法，不是本文的重点。在日常开发中经常结合Stream一起使用Optional，还是比较好用的。
      2. 另外一个值得一提的就是最近（2020年03月17日）发布的JDK14中对于NPE有了一个增强。那就是JEP358:Helpful NullPointerExceptions
         1. 该特性可以更好的提示哪个地方出现了空指针，需要通过`-XX:+ShowCodeDetailsInExceptionMessages` 开启
         2. 在未来的版本中，这个特性可能会默认开启。
         3. 这个增强特性不仅适用于方法调用，只要会导致NullPointerException的地方也都适用，包括字段的访问、数据的访问和赋值。

3. JEP359:Recode（预览特性）

   1. 官方吐槽最为致命

      早在2019年2月份，Java 语言架构师 Brian Goetz，曾经写过一篇文章，详尽的说明了并吐槽了Java语言，他和很多程序员一样抱怨“Java太啰嗦”或有太多的“繁文缛节”，他提到：开发人员想要创建纯数据载体类（plain data carriers）通常都必须编写大量低价值、 重复的、容易出错的代码。如：构造函数、getter/setter、equals()、hashCode()以及 toString()等。

      以至于很多人选择使用IDE的功能来自动生成这些代码。还有一些开发会选择使用一些第三方类库，如Lombok等来生成这些方法，从而会导致了令人吃惊的表现（surprising behavior）和糟糕的可调试性（poor debuggability）。

   2. 神说要用Record，于是就有了

   3. 

## 其他新特性

## 写在最后

