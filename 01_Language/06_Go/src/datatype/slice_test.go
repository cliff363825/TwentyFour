package datatype

import (
	"fmt"
	"testing"
)

func TestSlice1(t *testing.T) {
	var intArr [5]int = [5]int{1, 22, 33, 66, 99}
	fmt.Printf("intArr=(%T)%v\n", intArr, intArr) // intArr=([5]int)[1 22 33 66 99]

	// 声明/定义一个切片
	// slice := intArr[1:3]
	// 1. slice 就是切片名
	// 2. intArr[1:3] 表示 slice 引用到 intArr 这个数组
	// 3. 引用 intArr 数组的起始下标为 1，最后的下标为 3（但是不包含3）
	slice := intArr[1:3]
	// slice=([]int)[22 33], len=2, cap=4
	fmt.Printf("slice=(%T)%v, len=%v, cap=%v\n", slice, slice, len(slice), cap(slice)) // 切片的容量是可以动态变化的
	// &slice[0]=(*int)0xc000018128, &intArr[1]=(*int)0xc000018128
	fmt.Printf("&slice[0]=(%T)%v, &intArr[1]=(%T)%v\n", &slice[0], &slice[0], &intArr[1], &intArr[1])

	slice[0] = 222
	fmt.Printf("intArr=(%T)%v\n", intArr, intArr) // intArr=([5]int)[1 222 33 66 99]
}

func TestSlice2(t *testing.T) {
	var slice []float64 = make([]float64, 5, 10) // 对于切片，必须make使用
	slice[1] = 10
	slice[3] = 20
	// slice=([]float64)[0 10 0 20 0], len=5, cap=10
	fmt.Printf("slice=(%T)%v, len=%v, cap=%v\n", slice, slice, len(slice), cap(slice))
}

func TestSlice3(t *testing.T) {
	var slice []string = []string{"tom", "jack", "mary"}
	fmt.Printf("slice=(%T)%v, len=%v, cap=%v\n", slice, slice, len(slice), cap(slice))
}

func TestSlice4(t *testing.T) {
	var arr [5]int = [5]int{10, 20, 30, 40, 50}
	//var slice = arr[1:4]
	var slice = arr[:]

	// 使用常规的for循环遍历切片
	for i := 0; i < len(slice); i++ {
		fmt.Printf("slice[%v]=(%T)%v\n", i, slice[i], slice[i])
	}

	// 使用for-range 方式遍历切片
	for k, v := range slice {
		fmt.Printf("slice[%v]=(%T)%v\n", k, v, v)
	}
}

func TestSlice5(t *testing.T) {
	// 用 append 内置函数，可以对切片进行动态追加
	var slice []int = []int{100, 200, 300}
	// 通过 append 直接给 slice 追加具体的元素
	var slice2 = append(slice, 400, 500, 600)

	fmt.Printf("slice=(%T)%v\n", slice, slice)
	fmt.Printf("slice2=(%T)%v\n", slice2, slice2)

	// 通过 append 将切片 slice 追加给 slice
	slice2 = append(slice, slice...)
	fmt.Printf("slice2=(%T)%v\n", slice2, slice2)

	// 切片 append 操作的底层原理分析
	// 切片 append 操作的本质就是对数组扩容
	// go 底层会创建一个新的数组 newArr(安装扩容后大小)
	// 将 slice 原来包含的元素拷贝到新的数组 newArr
	// slice 重新引用到 newArr
	// 注意 newArr 是在底层来维护的，程序员不可见
}

func TestSlice6(t *testing.T) {
	// append(s, args...) 的个人理解
	// 当我们往切片里面append的时候，如果超过了切片的容量，那就要向系统申请一块新的内存空间，将原来切片上的数据进行值拷贝，然后返回新的内存空间地址。
	// 如果没有超过切片的容量，则还是原来的那块内存空间，原来的内存空间地址。
	s := []int{5}

	s = append(s, 7)
	fmt.Println("cap(s) =", cap(s), "ptr(s) =", &s[0])

	s = append(s, 9)
	fmt.Println("cap(s) =", cap(s), "ptr(s) =", &s[0])

	x := append(s, 11)
	fmt.Println("cap(s) =", cap(s), "ptr(s) =", &s[0], "ptr(x) =", &x[0])

	y := append(s, 12)
	fmt.Println("cap(s) =", cap(s), "ptr(s) =", &s[0], "ptr(y) =", &y[0])

	fmt.Println("x=", x)
	fmt.Println("y=", y)
	fmt.Println("s=", s)
}

func TestSlice7(t *testing.T) {
	// 切片的拷贝操作
	// 切片使用copy内置函数完成拷贝，举例说明
	var srcSlice []int = []int{1, 2, 3, 4, 5}
	var destSlice []int = make([]int, 10)

	copy(destSlice, srcSlice)
	// srcSlice=([]int)[1 2 3 4 5], &srcSlice=(*[]int)&[1 2 3 4 5]
	fmt.Printf("srcSlice=(%T)%v, &srcSlice=(%T)%v\n", srcSlice, srcSlice, &srcSlice, &srcSlice)
	// destSlice=([]int)[1 2 3 4 5 0 0 0 0 0], &destSlice=(*[]int)&[1 2 3 4 5 0 0 0 0 0]
	fmt.Printf("destSlice=(%T)%v, &destSlice=(%T)%v\n", destSlice, destSlice, &destSlice, &destSlice)

	// copy(dst, src) 参数的数据类型是切片
	// 按照上面的代码来看，srcSlice 和 destSlice 的数据空间是独立，相互不影响，也就是说 srcSlice[0]=999，destSlice[0]仍然是1
}

func TestStringSlice(t *testing.T) {
	// string 底层是一个 byte 数组，因此 string 也可以进行切片处理
	var str = "hello@onevgo.com"

	// 使用切片获取到 onevgo
	var s string = str[6:]
	fmt.Printf("s=(%T)%v\n", s, s)

	// string 是不可变的，也就是说不能通过 str[0] = 'z' 方式来修改字符串
	//str[0] = 'z' // 编译不会通过，报错，原因是 string 是不可变的

	// 如果需要修改字符串，可以现将 string -> []byte or []rune -> 修改 -> 重写转成 string
	var arr []byte = []byte(str)
	arr[0] = 'z'
	var str1 string = string(arr)
	fmt.Printf("str1=(%T)%v\n", str1, str1)

	// 细节，我们转成 []byte 后，可以处理英文和数字，但是不能处理中文
	// 原因是 []byte 字节来处理，而一个汉字，是3个字节，因此会出现乱码
	// 解决方法是将 string 转成 []rune即可，因为 []rune 是按字符处理，兼容汉字
	//var arr1 []byte = []byte(str)
	//arr1[0] = '北' // constant 21271 overflows byte

	var arr1 []rune = []rune(str)
	arr1[0] = '北'
	str1 = string(arr1)
	fmt.Printf("str1=(%T)%v\n", str1, str1)
}
