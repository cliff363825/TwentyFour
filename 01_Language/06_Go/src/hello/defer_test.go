package hello

import (
	"fmt"
	"testing"
)

func Sum(n1 int, n2 int) int {
	// 当执行到 defer 时，暂时不执行，会将 defer 后面的语句压入到独立的栈中
	// 当函数执行完毕后，再从 defer 栈，按照后进先出的方式出栈，执行
	defer fmt.Println("ok1 n1=", n1) // 3. ok1 n1= 10
	defer fmt.Println("ok2 n2=", n2) // 2. ok2 n2= 20

	n1++
	n2++
	res := n1 + n2
	fmt.Println("ok3 res=", res) // 1. ok3 res= 30
	return res
}

func TestDefer(t *testing.T) {
	res := Sum(10, 20)
	fmt.Println("res=", res)
}
