package datatype

import (
	"fmt"
	"testing"
	"unsafe"
)

func TestInt(t *testing.T) {
	//var i int8
	//i = -129 // constant -129 overflows int8

	var i int32
	var j int

	// i=(int32)0, Sizeof(int32)=4
	fmt.Printf("i=(%T)%v, Sizeof(int32)=%v\n", i, i, unsafe.Sizeof(i))

	// j=(int)0, Sizeof(int)=8
	fmt.Printf("j=(%T)%v, Sizeof(int)=%v\n", j, j, unsafe.Sizeof(j))

	// 说明，如果运算的数都是整数，那么除后，去掉小数部分，保留整数部分
	var f1 = 10 / 4
	fmt.Printf("f1=(%T)%v\n", f1, f1) // f1=(int)2

	// 如果我们希望保留小数部分，则需要有浮点数参与运算
	var f2 float64 = 10.0 / 4
	fmt.Printf("f2=(%T)%v\n", f2, f2) // f2=(float64)2.5

	// 在 golang 中，++ 和 -- 只能独立使用
	var k int = 8
	var l int
	//l = k++ // 错误，k++只能独立使用
	//l = k-- // 错误，k--只能独立使用
	k++
	l = k

	//++k // 错误，在golang中没有前++
	//--k // 错误，在golang中没有前--
	fmt.Printf("l=(%T)%v\n", l, l)
}
