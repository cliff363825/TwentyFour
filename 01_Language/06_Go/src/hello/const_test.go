package hello

import (
	"fmt"
	"testing"
)

func TestConst1(t *testing.T) {
	var num int
	num = 90

	// 常量在声明的时候，必须赋值。
	const tax int = 0

	// 常量是不能修改
	//tax = 10

	// 常量只能修饰 bool，数值类型（int，float系列），string 类型

	fmt.Println("num=", num, "tax=", tax)
}

func TestConst2(t *testing.T) {
	// iota是一个预先声明的标识符，表示（通常带括号的）const声明中当前const规范的无类型整数序数。
	// 它是零索引的。
	const (
		a    = iota       // 0
		b, c = iota, iota // 1，1
		d    = iota       // 2
	)
	fmt.Println(a, b, c, d) // 0 1 1 2

	const (
		e = iota // 0
		f = 3    // 3
		g        // 3
	)
	fmt.Println(e, f, g) // 0 3 3
}
