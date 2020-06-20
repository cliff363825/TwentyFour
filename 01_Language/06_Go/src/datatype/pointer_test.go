package datatype

import (
	"fmt"
	"testing"
	"unsafe"
)

func TestPointer(t *testing.T) {
	// 基本数据类型在内存布局
	var i int = 10
	// i 的地址是什么 &i
	fmt.Printf("i=(%T)%v, &i=(%T)%v, Sizeof(*int)=%v\n", i, i, &i, &i, unsafe.Sizeof(&i)) // i=(int)10, &i=(*int)0xc000076198, Sizeof(*int)=8

	// 下面的 var ptr *int = &i
	// 1. ptr 是一个指针变量
	// 2. ptr 的类型 *int
	// 3. ptr 本身的值 &i
	var ptr *int = &i
	fmt.Printf("ptr=(%T)%v, &ptr=(%T)%v, *ptr=(%T)%v\n", ptr, ptr, &ptr, &ptr, *ptr, *ptr) // ptr=(*int)0xc000076198, &ptr=(**int)0xc0000ca000, *ptr=(int)10
}

func TestPointer2(t *testing.T) {
	var num int = 9
	fmt.Printf("num=(%T)%v\n", num, num)

	var ptr *int
	ptr = &num
	*ptr = 10
	fmt.Printf("num=(%T)%v\n", num, num)
}

func TestPointer3(t *testing.T) {
	num := 1
	num = valueIncr(num)
	fmt.Printf("num=(%T)%v\n", num, num)

	pointerIncr(&num)
	fmt.Printf("num=(%T)%v\n", num, num)
}

func valueIncr(num int) int {
	return num + 1
}

func pointerIncr(ptr *int) {
	*ptr = *ptr + 1
}

type Employee struct {
	Name string
}

func TestPointer4(t *testing.T) {
	var e Employee = Employee{
		Name: "张三",
	}
	fmt.Printf("e=(%T)%v\n", e, e) // e=(datatype.Employee){张三}

	var eptr *Employee = &e
	eptr.Name = "李四"               // ok
	fmt.Printf("e=(%T)%v\n", e, e) // e=(datatype.Employee){李四}

	(*eptr).Name = "王五"            // ok
	fmt.Printf("e=(%T)%v\n", e, e) // e=(datatype.Employee){王五}

	pointerToPointer(&eptr)
	fmt.Printf("e=(%T)%v\n", e, e)                                     // e=(datatype.Employee){王五}
	fmt.Printf("eptr=(%T)%v,*eptr=(%T)%v\n", eptr, eptr, *eptr, *eptr) // eptr=(*datatype.Employee)&{赵六},*eptr=(datatype.Employee){赵六}
}

func pointerToPointer(ptr **Employee) {
	// 0x01(e) 						= (Employee){}
	// 0x02(eptr) 					= (*Employee)0x01
	// 0x03(pointerToPointer.ptr)	= (**Employee)0x02
	*ptr = new(Employee) // 将 *ptr 指向新的内存空间
	// 0x02(eptr)					= (*Employee)0x04
	(**ptr).Name = "赵六"
}
