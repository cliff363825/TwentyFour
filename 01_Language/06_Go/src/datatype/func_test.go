package datatype

import (
	"fmt"
	"reflect"
	"testing"
)

func getSum(n1 int, n2 int) int {
	return n1 + n2
}

func TestFunc1(t *testing.T) {
	// 在Go中，函数也是一种数据类型
	// 可以赋值给一个变量，则该变量就是一个函数类型的变量了。通过该变量可以对函数调用
	var f func(int, int) int
	fmt.Printf("f=(%T)%v\n", f, reflect.ValueOf(f)) // f=(func(int, int) int)<nil>

	f = getSum
	fmt.Printf("f=(%T)%v,&f=(%T)%v\n", f, reflect.ValueOf(f), &f, &f) // f=(func(int, int) int)0x110ba00,&f=(*func(int, int) int)0xc00000e028

	var res = f(10, 40)
	fmt.Printf("res=(%T)%v\n", res, res)
}

// 函数既然是一种数据类型，因此在Go中，函数可以作为形参，并且调用
func valueFunc(funcvar func(int, int) int, num1 int, num2 int) int {
	return funcvar(num1, num2)
}

func pointerFunc(funcptr *func(int, int) int, num1 int, num2 int) int {
	*funcptr = getSub
	return (*funcptr)(num1, num2)
}

func getSub(n1 int, n2 int) int {
	return n1 - n2
}

func TestFunc2(t *testing.T) {
	var f = getSum
	res1 := valueFunc(f, 50, 60)
	fmt.Println("res1=", res1) // res1= 110

	res2 := pointerFunc(&f, 50, 60)
	fmt.Println("res2=", res2) // res2= -10
}

type myFuncType func(int, int) int

func myFunc(funcvar myFuncType, num1 int, num2 int) int {
	return funcvar(num1, num2)
}

func TestFunc3(t *testing.T) {
	// 给 int 取了别名，在 go 中 myInt 和 int 虽然都是 int 类型
	// 但是 go 认为 myInt 和 int 两个类型
	type myInt int

	var num1 myInt
	var num2 int
	num1 = 40
	//num2 = num1 // cannot use num1 (type myInt) as type int in assignment
	num2 = int(num1)
	fmt.Printf("num1=(%T)%v, num2=(%T)%v\n", num1, num1, num2, num2) // num1=(datatype.myInt)40, num2=(int)40

	res := myFunc(getSum, 70, 80)
	fmt.Println("res=", res)
}

// 支持对函数返回值命名
func getSumAndSub(n1 int, n2 int) (sum int, sub int) {
	sum = n1 + n2
	sub = n1 - n2
	return
}

func TestFunc4(t *testing.T) {
	var res1, res2 = getSumAndSub(100, 90)
	fmt.Printf("res1=(%T)%v, res2=(%T)%v\n", res1, res1, res2, res2) // res1=(int)190, res2=(int)10
}

func sum(a0 int, args ...int) int {
	sum := a0
	for _, v := range args {
		sum += v
	}

	return sum
}

func TestFunc5(t *testing.T) {
	i := sum(1, 2, 3, 4, 5)
	fmt.Println("i=", i)
}

func swap(num1 *int, num2 *int) {
	var tmp = *num1
	*num1 = *num2
	*num2 = tmp
}

func TestFunc6(t *testing.T) {
	num1 := 10
	num2 := 20

	fmt.Printf("num1=(%T)%v, num2=(%T)%v\n", num1, num1, num2, num2) // num1=(int)10, num2=(int)20
	swap(&num1, &num2)
	fmt.Printf("num1=(%T)%v, num2=(%T)%v\n", num1, num1, num2, num2) // num1=(int)20, num2=(int)10
}

// 斐波那契数列 1,1,2,3,5,8,13...
// 给你一个整数，求出它的斐波那契数是多少？
func fibonacci(n int) int {
	if n == 1 || n == 2 {
		return 1
	}

	return fibonacci(n-1) + fibonacci(n-2)
}

func TestFunc7(t *testing.T) {
	fmt.Println("fibonacci(3)=", fibonacci(3))
	fmt.Println("fibonacci(4)=", fibonacci(4))
	fmt.Println("fibonacci(5)=", fibonacci(5))
	fmt.Println("fibonacci(6)=", fibonacci(6))
}

var (
	globalFunc = func(a0 int, a1 int) (sum int, sub int) {
		sum = a0 + a1
		sub = a0 - a1
		return
	}
)

func TestFunc8(t *testing.T) {
	// 在定义匿名函数是就直接调用，这种方式匿名函数只能调用一次
	// 求两个数的和，使用匿名函数的方式完成
	var sumFunc = func(a int, b int) int {
		return a + b
	}
	fmt.Println("a+b=", sumFunc(3, 5))

	res1, res2 := globalFunc(15, 7)
	fmt.Println("res1=", res1, "res2=", res2)
}
