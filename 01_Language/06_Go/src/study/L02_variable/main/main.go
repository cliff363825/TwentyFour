package main

import (
	"fmt"
)

// 如何一次性声明多个全局变量
// 定义全局变量
var n1 = 100
var n2 = 200
var name = "jack"

// 上面的声明方式，也可以改成一次性声明
var (
	n3    = 300
	n4    = 900
	name2 = "mary"
)

func main() {
	// Golang 变量使用的三种方式
	// 1. 指定变量类型，声明后若不赋值，使用零值 (zero value)
	// int 的零值是 0
	var i int
	fmt.Println("i=", i) // i = 0

	// 2. 根据值自行判定变量类型（类型推导）
	var num = 10.11
	fmt.Println("num=", num) // num = 10.11

	// 3. 省略 var，注意 := 左侧的变量不应该是已经声明过的，否则会导致编译错误
	// 下面的方式等价 var name string; name = "tom";
	// := 的 : 不能省略，否则错误
	name := "tom"
	fmt.Println("name=", name)

	// 4. 多变量声明
	// 一次性声明多个变量
	var n1, n2, n3 int
	fmt.Println("n1=", n1, "n2=", n2, "n3=", n3)

	// 一次性声明多个变量的方式2
	var n11, name12, n13 = 100, "green", 888
	fmt.Println("n11=", n11, "name12=", name12, "n13=", n13)

	// 一次性声明多个变量的方式3，同样可以使用类型推导
	n21, name22, n23 := 100, "green1", 888
	fmt.Println("n21=", n21, "name22=", name22, "n23=", n23)

	// 5. 该区域的数据值可以在同一类型范围内不断变化
	// 6. 变量在同一个作用域（在一个函数或者在代码块）内不能重名
	// 7. 变量=变量名+值+数据类型
	// 8. Golang 的变量如果没有赋初值，编译器会使用默认值，比如 int 零值 0，string 零值 ""，float 零值 0
}
