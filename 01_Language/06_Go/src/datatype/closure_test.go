package datatype

import (
	"fmt"
	"strings"
	"testing"
)

func TestClosure(t *testing.T) {
	f := counter()
	fmt.Println(f(1))
	fmt.Println(f(2))
	fmt.Println(f(3))

	w := wrapper(&f)
	fmt.Println(w(4))
	fmt.Println(w(5))
	fmt.Println(w(6))
}

func TestClosure2(t *testing.T) {
	f := makeSuffix(".jpg")
	fmt.Println("文件名处理后=", f("winter"))
	fmt.Println("文件名处理后=", f("bird.jpg"))
}

// 累加器
func counter() func(int) int {
	var n int = 10
	return func(x int) int {
		n = n + x
		return n
	}
}

func wrapper(a0 *func(int) int) func(int) int {
	return func(i int) int {
		fmt.Printf("call func(%d)...\n", i)
		return (*a0)(i)
	}
}

func makeSuffix(suffix string) func(string) string {
	return func(name string) string {
		// 如果 name 没有指定后缀，则加上，否则就返回原来的名字
		if !strings.HasSuffix(name, suffix) {
			return name + suffix
		}

		return name
	}
}
