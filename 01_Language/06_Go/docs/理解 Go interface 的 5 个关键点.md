# [理解 Go interface 的 5 个关键点](https://sanyuesha.com/2017/07/22/how-to-understand-go-interface/)

## 1、interface 是一种类型

```
type I interface {
    Get() int
}
```

首先 **interface 是一种类型**，从它的定义可以看出来用了 type 关键字，更准确的说 interface 是一种**具有一组方法的类型**，这些方法定义了 interface 的行为。

go 允许不带任何方法的 interface ，这种类型的 interface 叫 **empty interface**。

**如果一个类型实现了一个 interface 中所有方法，我们说类型实现了该 interface**，所以所有类型都实现了 empty interface，因为任何一种类型至少实现了 0 个方法。go 没有显式的关键字用来实现 interface，只需要实现 interface 包含的方法即可。



## 2、interface 变量存储的是实现者的值

```
//1
type I interface {    
    Get() int
    Set(int)
}

//2
type S struct {
    Age int
}

func(s S) Get()int {
    return s.Age
}

func(s *S) Set(age int) {
    s.Age = age
}

//3
func f(i I){
    i.Set(10)
    fmt.Println(i.Get())
}

func main() {
    s := S{} 
    f(&s)  //4
}
```

这段代码在 `#1` 定义了 interface I，在 `#2` 用 struct S 实现了 I 定义的两个方法，接着在 `#3` 定义了一个函数 f 参数类型是 I，S 实现了 I 的两个方法就说 S 是 I 的实现者，执行 `f(&s)` 就完了一次 interface 类型的使用。

interface 的重要用途就体现在**函数 f 的参数中**，如果有多种类型实现了某个 interface，**这些类型的值都可以直接使用 interface 的变量存储**。

```
s := S{}
var i I //声明 i 
i = &s //赋值 s 到 i
fmt.Println(i.Get())
```



不难看出 interface 的变量中存储的是实现了 interface 的类型的对象值，这种能力是 [duck typing](http://en.wikipedia.org/wiki/Duck_typing)。在使用 interface 时不需要显式在 struct 上声明要实现哪个 interface ，只需要实现对应 interface 中的方法即可，go 会自动进行 interface 的检查，并在运行时执行从其他类型到 interface 的自动转换，即使实现了多个 interface，go 也会在使用对应 interface 时实现自动转换，这就是 interface 的魔力所在。

## 3、如何判断 interface 变量存储的是哪种类型

一个 interface 被多种类型实现时，有时候我们需要区分 interface 的变量究竟存储哪种类型的值，go 可以使用 `comma, ok` 的形式做区分 `value, ok := em.(T)`：**em 是 interface 类型的变量，T代表要断言的类型，value 是 interface 变量存储的值，ok 是 bool 类型表示是否为该断言的类型 T**。

```
if t, ok := i.(*S); ok {
    fmt.Println("s implements I", t)
}
```

ok 是 true 表明 i 存储的是 *S 类型的值，false 则不是，这种区分能力叫 [Type assertions](https://tour.golang.org/methods/15) (类型断言)。

如果需要区分多种类型，可以使用 switch 断言，更简单直接，这种断言方式只能在 switch 语句中使用。

```
switch t := i.(type) {
case *S:
    fmt.Println("i store *S", t)
case *R:
    fmt.Println("i store *R", t)
}
```

## 4、空的 interface

`interface{}` 是一个空的 interface 类型，根据前文的定义：一个类型如果实现了一个 interface 的所有方法就说该类型实现了这个 interface，空的 interface 没有方法，所以可以认为所有的类型都实现了 `interface{}`。如果定义一个函数参数是 `interface{}` 类型，这个函数应该可以接受任何类型作为它的参数。

```
func doSomething(v interface{}){    
}
```

如果函数的参数 v 可以接受任何类型，那么函数被调用时在函数内部 v 是不是表示的是任何类型？并不是，虽然函数的参数可以接受任何类型，并不表示 v 就是任何类型，在函数 doSomething 内部 v 仅仅是一个 interface 类型，之所以函数可以接受任何类型是在 go 执行时传递到函数的任何类型都被自动转换成 `interface{}`。go 是如何进行转换的，以及 v 存储的值究竟是怎么做到可以接受任何类型的，感兴趣的可以看看 [Russ Cox 关于 interface 的实现](https://research.swtch.com/interfaces) 。

既然空的 interface 可以接受任何类型的参数，那么一个 `interface{}`类型的 slice 是不是就可以接受任何类型的 slice ?

```
func printAll(vals []interface{}) { //1
	for _, val := range vals {
		fmt.Println(val)
	}
}

func main(){
	names := []string{"stanley", "david", "oscar"}
	printAll(names)
}
```

上面的代码是按照我们的假设修改的，执行之后竟然会报 `cannot use names (type []string) as type []interface {} in argument to printAll` 错误，why？

这个错误说明 go 没有帮助我们自动把 slice 转换成 `interface{}` 类型的 slice，所以出错了。**go 不会对 类型是`interface{}` 的 slice 进行转换** 。为什么 go 不帮我们自动转换，一开始我也很好奇，最后终于在 go 的 wiki 中找到了答案 https://github.com/golang/go/wiki/InterfaceSlice 大意是 `interface{}` 会占用两个字长的存储空间，一个是自身的 methods 数据，一个是指向其存储值的指针，也就是 interface 变量存储的值，因而 slice []interface{} 其长度是固定的`N*2`，但是 []T 的长度是`N*sizeof(T)`，两种 slice 实际存储值的大小是有区别的(文中只介绍两种 slice 的不同，至于为什么不能转换猜测可能是 runtime 转换代价比较大)。

但是我们可以手动进行转换来达到我们的目的。

```
var dataSlice []int = foo()
var interfaceSlice []interface{} = make([]interface{}, len(dataSlice))
for i, d := range dataSlice {
	interfaceSlice[i] = d
}
```

## 5、interface 的实现者的 receiver 如何选择

在我们上文的例子中调用 f 是 `f(&s)` 也就是 S 的指针类型，为什么不能是 `f(s)` 呢，如果是 s 会有什么问题？改成 f(s) 然后执行代码。

```
cannot use s (type S) as type I in argument to f:
	S does not implement I (Set method has pointer receiver)
```

这个错误的意思是 S 没有实现 I，哪里出了问题？**关键点是 S 中 set 方法的 receiver 是个 pointer \*S** 。

interface 定义时并没有严格规定实现者的方法 receiver 是个 value receiver 还是 pointer receiver，上面代码中的 S 的 Set receiver 是 pointer，也就是实现 I 的两个方法的 receiver 一个是 value 一个是 pointer，使用 `f(s)`的形势调用，传递给 f 的是个 s 的一份拷贝，在进行 s 的拷贝到 I 的转换时，s 的拷贝不满足 Set 方法的 receiver 是个 pointer，也就没有实现 I。**go 中函数都是按值传递即 passed by value**。

那反过来会怎样，如果 receiver 是 value，函数用 pointer 的形式调用？

```
type I interface {
	Get() int
	Set(int)
}

type SS struct {
	Age int
}

func (s SS) Get() int {
	return s.Age
}

func (s SS) Set(age int) {
	s.Age = age
}

func f(i I) {
	i.Set(10)
	fmt.Println(i.Get())
}

func main(){
  	ss := SS{}
	f(&ss) //ponter
	f(ss)  //value
}
```

I 的实现者 SS 的方法 receiver 都是 value receiver，执行代码可以看到无论是 pointer 还是 value 都可以正确执行。

导致这一现象的原因是什么？

如果是按 pointer 调用，go 会自动进行转换，因为有了指针总是能得到指针指向的值是什么，如果是 value 调用，go 将无从得知 value 的原始值是什么，因为 value 是份拷贝。**go 会把指针进行隐式转换得到 value，但反过来则不行**。

对于 receiver 是 value 的 method，任何在 method 内部对 value 做出的改变都不影响调用者看到的 value，这就是按值传递。

另一个说明上述现象的例子是这样的来自 https://play.golang.org/p/TvR758rfre

```
package main

import (
	"fmt"
)

type Animal interface {
	Speak() string
}

type Dog struct {
}

func (d Dog) Speak() string {
	return "Woof!"
}

type Cat struct {
}

//1
func (c *Cat) Speak() string {
	return "Meow!"
}

type Llama struct {
}

func (l Llama) Speak() string {
	return "?????"
}

type JavaProgrammer struct {
}

func (j JavaProgrammer) Speak() string {
	return "Design patterns!"
}

func main() {
	animals := []Animal{Dog{}, Cat{}, Llama{}, JavaProgrammer{}}
	for _, animal := range animals {
		fmt.Println(animal.Speak())
	}
}
```

`#1` Cat 的 speak receiver 是 pointer，interface Animal 的 slice，Cat 的值是一个 value，同样会因为 receiver 不一致而导致无法执行。

## 参考资料

- https://github.com/astaxie/build-web-application-with-golang/blob/master/zh/02.6.md
- http://jordanorelli.com/post/32665860244/how-to-use-interfaces-in-go
- https://tour.golang.org/methods/15
- https://www.miek.nl/go/#interfaces
- https://github.com/golang/go/wiki/InterfaceSlice
- https://play.golang.org/p/TvR758rfre
- https://golang.org/doc/effective_go.html#interfaces
- http://en.wikipedia.org/wiki/Duck_typing

## 文中用到的代码

[点击下载源码包](https://gist.github.com/zhyq0826/bda38dd5525c0157829bc574e742463c/archive/b3d1a53050dca568767b0efdb83968e67a9c6461.zip)

```
package main

import (
    "fmt"
)

//1
type I interface {
    Get() int
    Set(int)
}

//2
type S struct {
    Age int
}

func (s S) Get() int {
    return s.Age
}

func (s *S) Set(age int) {
    s.Age = age
}

type SS struct {
    Age int
}

func (s SS) Get() int {
    return s.Age
}

func (s SS) Set(age int) {
    s.Age = age
}

//3
func f(i I) {
    i.Set(10)
    fmt.Println(i.Get())
}

type R struct{ i int }

func (p *R) Get() int  { return p.i }
func (p *R) Set(v int) { p.i = v }

func doSomething(v interface{}) {
    fmt.Println("doSomething called")
}

func printAll(vals []interface{}) {
    for _, val := range vals {
        fmt.Println(val)
    }
}

type Animal interface {
    Speak() string
}

type Dog struct {
}

func (d Dog) Speak() string {
    return "Woof!"
}

type Cat struct {
}

func (c *Cat) Speak() string {
    return "Meow!"
}

type Llama struct {
}

func (l Llama) Speak() string {
    return "?????"
}

type JavaProgrammer struct {
}

func (j JavaProgrammer) Speak() string {
    return "Design patterns!"
}

func main() {
    s := S{}
    f(&s) //4

    ss := SS{}
    f(&ss) //ponter
    f(ss)  //value

    var i I
    i = &s
    fmt.Println(i.Get())

    if _, ok := i.(*S); ok {
        fmt.Println("i store *S")
    }

    switch t := i.(type) {
    case *S:
        fmt.Println("i store *S", t)
    case *R:
        fmt.Println("i store *R", t)
    }

    doSomething(&s)

    names := []string{"stanley", "david", "oscar"}
    vals := make([]interface{}, len(names))
    for index, value := range names {
        vals[index] = value
    }
    // printAll(names)
    printAll(vals)

    //cannot use Cat literal (type Cat) as type Animal in array or slice literal:
    // Cat does not implement Animal (Speak method has pointer receiver)
    //
    // animals := []Animal{Dog{}, Cat{}, Llama{}, JavaProgrammer{}}
    // for _, animal := range animals {
    //  fmt.Println(animal.Speak())
    // }
}
```

**更多阅读**

详解 Go interface 系列

- [Go interface 详解(一) ：介绍](https://sanyuesha.com/2017/10/10/go-interface-1/)
- [Go interface 详解(二) ：定义和使用](https://sanyuesha.com/2017/10/12/go-interface-2/)
- [Go interface 详解 (三) ：interface 的值](https://sanyuesha.com/2017/10/18/go-interface-3/)
- [Go interface 详解 (四) ：类型断言](https://sanyuesha.com/2017/12/01/go-interface-4/)