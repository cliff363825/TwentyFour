package builtin_

import (
	"fmt"
	"testing"
)

func TestNew(t *testing.T) {
	var num1 int
	fmt.Printf("num1=(%T)%v,&num1=(%T)%v\n", num1, num1, &num1, &num1) // num1=(int)0,&num1=(*int)0xc0000a0138

	// 内置函数 new 分配内存。第一个参数是类型，而不是值，返回的值是指向该类型新分配的零值的指针。
	var num2 = new(int)
	fmt.Printf("num2=(%T)%v,*num2=(%T)%v\n", num2, num2, *num2, *num2) // num2=(*int)0xc0000a0170,*num2=(int)0

	var myNew = func() *int {
		var i int
		return &i
	}
	var num3 = myNew()
	fmt.Printf("num3=(%T)%v,*num3=(%T)%v\n", num3, num3, *num3, *num3) // num3=(*int)0xc0000a0178,*num3=(int)0
}
