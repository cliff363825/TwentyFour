package builtin_

import (
	"fmt"
	"reflect"
	"testing"
)

type Point struct {
	X int
	Y int
}

func TestAssert(t *testing.T) {
	point := Point{1, 2}
	var obj interface{} = point          // ok
	fmt.Printf("obj=(%T)%v\n", obj, obj) // obj=(assert.Point){1 2}

	// 如何将 obj 赋给一个 Point变量？
	var point1 Point
	// point1 = obj 不可以
	// point1 = obj.(Point) // 可以
	point1 = obj.(Point)                          // 类型断言 point1 = obj instanceof Point ? obj : nil
	fmt.Printf("point1=(%T)%v\n", point1, point1) // point1=(assert.Point){1 2}
}

func TestAssert2(t *testing.T) {
	var x float32 = 1.1
	var obj interface{} = x // 空接口，可以接收任意类型

	y, ok := obj.(float32)
	//y, ok := obj.(float64)
	if ok {
		fmt.Println("convert success")
		fmt.Printf("y=(%T)%v\n", y, y)
	} else {
		fmt.Println("convert fail")
	}
}

func TestTypeJudge(t *testing.T) {
	var n1 float32 = 1.1
	var n2 float64 = 2.3
	var n3 int32 = 30
	var name string = "tom"
	address := "北京"

	n4 := 300
	var n5 byte = 1

	stu1 := Student{}
	stu2 := &Student{}

	typeJudge(n1, n2, n3, name, address, n4, stu1, stu2, n5)
}

func typeJudge(items ...interface{}) {
	for k, v := range items {
		switch v.(type) {
		case bool:
			fmt.Printf("第%v个参数是 bool 类型，值是 %v\n", k, v)
		case float32:
			fmt.Printf("第%v个参数是 float32 类型，值是 %v\n", k, v)
		case float64:
			fmt.Printf("第%v个参数是 float64 类型，值是 %v\n", k, v)
		case int, int32, int64:
			fmt.Printf("第%v个参数是 整数 类型，值是 %v\n", k, v)
		case string:
			fmt.Printf("第%v个参数是 string 类型，值是 %v\n", k, v)
		case Student:
			fmt.Printf("第%v个参数是 Student 类型，值是 %v\n", k, v)
		case *Student:
			fmt.Printf("第%v个参数是 *Student 类型，值是 %v\n", k, v)
		default:
			fmt.Printf("第%v个参数是 %v 类型，值是 %v\n", k, reflect.TypeOf(v), v)
		}
	}
}

type Student struct {
}
