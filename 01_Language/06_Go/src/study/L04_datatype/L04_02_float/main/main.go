package main

import (
	"fmt"
	"github.com/shopspring/decimal"
	"unsafe"
)

func main() {
	var f32 float32
	var f64 float64

	// f32=(float32)0, Sizeof(float32)=4
	fmt.Printf("f32=(%T)%v, Sizeof(float32)=%v\n", f32, f32, unsafe.Sizeof(f32))

	// f64=(float64)0, Sizeof(float64)=8
	fmt.Printf("f64=(%T)%v, Sizeof(float64)=%v\n", f64, f64, unsafe.Sizeof(f64))

	var c float64 = 3.1415925535
	fmt.Printf("%v -- %f -- %.2f\n", c, c, c)

	var f2 float32 = 3.14e2
	fmt.Printf("%v -- %f\n", f2, f2)

	// float精度丢失问题
	var f4 = 1129.6
	fmt.Println(f4 * 100) // 112959.99999999999

	var m1 = 8.2
	var m2 = 3.8
	fmt.Println(m1 - m2) // 4.3999999999999995

	m3 := decimal.NewFromFloat(m1).Sub(decimal.NewFromFloat(m2))
	fmt.Println(m3) // 4.4

	// 类型转换
	// int() / float32() / float64()
	var a = 10
	var b = float64(a)
	fmt.Printf("a=(%T)%v, b=(%T)%v\n", a, a, b, b)
}
