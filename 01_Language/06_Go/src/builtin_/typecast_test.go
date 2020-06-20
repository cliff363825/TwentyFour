package builtin_

import (
	"fmt"
	"strconv"
	"testing"
)

func TestTypeCast1(t *testing.T) {
	var i int = 100
	// 希望将 int => float
	var f1 float32 = float32(i)
	fmt.Printf("i=(%T)%v, f1=(%T)%v\n", i, i, f1, f1)

	var n1 int8
	n1 = n1 + 10
	//n1 = n1 + 128 // cannot convert constant 128 to type int8
	fmt.Printf("n1=(%T)%v\n", n1, n1)

	var num1 int = 99
	var num2 float64 = 23.456
	var b bool = true
	var myChar byte = 'h'
	var str string

	// 使用第一种方式来转换 fmt.Sprintf
	str = fmt.Sprintf("%d", num1)
	fmt.Printf("str=(%T)%v\n", str, str)

	str = fmt.Sprintf("%f", num2)
	fmt.Printf("str=(%T)%v\n", str, str)

	str = fmt.Sprintf("%t", b)
	fmt.Printf("str=(%T)%v\n", str, str)

	str = fmt.Sprintf("%c", myChar)
	fmt.Printf("str=(%T)%v\n", str, str)
}

func TestTypeCast2(t *testing.T) {
	var str string
	// 第二种方式 strconv 函数
	var num3 int = 99
	var num4 float64 = 23.456
	var b2 bool = false

	str = strconv.FormatInt(int64(num3), 10)
	fmt.Printf("str=(%T)%v\n", str, str)

	// 'f': 格式， 10: 表示小数位保留10位， 64: 表示这个小数是float64
	str = strconv.FormatFloat(num4, 'f', 10, 64)
	fmt.Printf("str=(%T)%v\n", str, str)

	str = strconv.FormatBool(b2)
	fmt.Printf("str=(%T)%v\n", str, str)

	// strconv 包中有一个函数 Itoa
	var num5 int64 = 4567
	str = strconv.Itoa(int(num5))
	fmt.Printf("str=(%T)%v\n", str, str)
}

func TestTypeCast3(t *testing.T) {
	var str string = "true"
	var b bool

	// 1. strconv.ParseBool(str) 函数会返回两个值 (bool, error)
	// 2. 因为我只想获取到 bool，不想获取 err 所以我使用 _ 忽略
	b, _ = strconv.ParseBool(str)
	fmt.Printf("b=(%T)%v\n", b, b)

	var str2 string = "1234590"
	var n1 int64
	var n2 int
	n1, _ = strconv.ParseInt(str2, 10, 64)
	n2 = int(n1)
	fmt.Printf("n1=(%T)%v\n", n1, n1)
	fmt.Printf("n2=(%T)%v\n", n2, n2)

	var str3 string = "123.456"
	var f1 float64
	f1, _ = strconv.ParseFloat(str3, 64)
	fmt.Printf("f1=(%T)%v\n", f1, f1)

	// 在将 String 类型转成 基本数据类型时，要确保 String 类型能够转成有效的数据，比如 我们可以把 "123" 转成一个整数，
	// 但是不能把 "hello" 转成一个整数，如果这么做，Golang 直接将其转成 0，其他类型也是一样的道理，float => 0, bool => false
	var str4 string = "hello"
	var n3 int64 = 11
	n3, _ = strconv.ParseInt(str4, 10, 64)
	fmt.Printf("n3=(%T)%v\n", n3, n3)
}
