package hello

import (
	"fmt"
	"testing"
)

// 函数外部声明/定义的变量叫全局变量
// 作用域在整个包都有效，如果其首字母为大写，则作用域在整个程序有效
var age1 int = 50
var Name1 string = "jack~"
// Name2 := "mike~" // syntax error: non-declaration statement outside function body
// 函数体外非声明语句

func TestScope(t *testing.T) {
	// age 和 Name 的作用域就只在 test 函数内部
	var age1 = 10
	var Name1 = "tom~"
	fmt.Println("age1=", age1)
	fmt.Println("Name1=", Name1)
}

func TestScope1(t *testing.T) {
	fmt.Println("age1=", age1)
	fmt.Println("Name1=", Name1)
}
