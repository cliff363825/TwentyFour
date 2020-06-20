package datatype

import (
	"fmt"
	"testing"
)

func TestString(t *testing.T) {
	var str string
	fmt.Printf("str=(%T)%v\n", str, str)

	// 字符串一旦赋值，字符串就不能修改了
	str = "hello world"
	//str[0] = 'c' // 这里就不能去修改str的内容，即go中的字符串是不可变的
	fmt.Printf("str=(%T)%v\n", str, str)

	// 1. 双引号，会识别转义字符
	str = "abc\nabc"
	fmt.Printf("str=(%T)%#v\n", str, str)

	// 2. 反引号，以字符串的原生形式输出，包括换行和特殊字符
	str = `abc\nabc`
	fmt.Printf("str=(%T)%v\n", str, str)

	// 字符串拼接
	str = "Hello"
	// 当一个拼接的操作很长时，可以分行写，需要将 + 保留在行尾
	str = str +
		" " +
		"world"
	str += "!"
	fmt.Printf("str=(%T)%v\n", str, str)

	var a int          // 0
	var b float32      // 0
	var c float64      // 0
	var isMarried bool // false
	var name string    // ""
	// 这里的 %v 表示按照变量的值输出
	fmt.Printf("a=%d, b=%f, c=%f, isMarried=%t, name=%s, name=%#v\n", a, b, c, isMarried, name, name)
}
