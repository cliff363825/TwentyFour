package main

import (
	"fmt"
)

func main() {
	var num int
	num = 90

	// 常量在声明的时候，必须赋值。
	const tax int = 0

	// 常量是不能修改
	//tax = 10

	// 常量只能修饰 bool，数值类型（int，float系列），string 类型
	fmt.Println("num=", num, "tax=", tax)

	// iota是一个预先声明的标识符，表示（通常带括号的）const声明中当前const规范的无类型整数序数。
	// 它是零索引的。
	// iota相当于excel中的自动填充
	const (
		a    = iota       // iota=0
		b, c = iota, iota // iota=1
		d    = iota       // iota=2
	)
	fmt.Println(a, b, c, d) // 0 1 1 2

	const (
		e = iota // iota=0
		f = 3    // iota=1
		g        // iota=2
		h = iota // iota=3
	)
	fmt.Println(e, f, g, h) // 0 3 3 3

	const (
		i, j = iota + 1, iota + 2 // iota=0
		k, l                      // iota=1
		m, n = iota + 1, iota + 2 // iota=2
	)
	fmt.Println(i, j, k, l, m, n) // 1 2 2 3 3 4
}
