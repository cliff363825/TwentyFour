package datatype

import (
	"fmt"
	"testing"
)

func TestChar(t *testing.T) {
	var c = 'b'
	// 字符实际存储类型是 int32，存储对应的 unicode 码值
	fmt.Printf("c=(%T)%v\n", c, c) // c=(int32)98

	// 如果我们希望输出对应的字符，需要使用格式化输出
	fmt.Printf("c=(%T)%c\n", c, c) // c=(int32)b

	c = '北'
	fmt.Printf("c=(%T)%c\n", c, c) // c=(int32)北

	// 可以直接给某个变量赋一个数字，然后按格式化输出时%c，会输出该数字对应的 unicode 字符
	var code = 21271
	fmt.Printf("code=(%T)%c\n", code, code) // code=(int)北

	// 由于字符类型是 int32，是可以运算的，运算时是按照码值运行的
	code = 10 + 'a'
	fmt.Printf("code=(%T)%c\n", code, code) // code=(int)k
}
