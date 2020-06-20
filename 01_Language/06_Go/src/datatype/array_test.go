package datatype

import (
	"fmt"
	"strconv"
	"testing"
)

func TestArray(t *testing.T) {
	var hens [6]float64

	hens[0] = 3.0
	hens[1] = 5.0
	hens[2] = 1.0
	hens[3] = 3.4
	hens[4] = 2.0
	hens[5] = 50.0

	var totalWeight float64 = 0
	/*
	for i := 0; i < len(hens); i++ {
		totalWeight += hens[i]
	}
	*/
	for i, j := 0, len(hens); i < j; i++ {
		totalWeight += hens[i]
	}

	var avgWeight float64 = totalWeight / float64(len(hens))

	fmt.Println("totalWeight=", totalWeight, "avgWeight=", strconv.FormatFloat(avgWeight, 'f', 2, 64))
}

func TestArray1(t *testing.T) {
	var arr [4]int32

	//arr   =([4]int32)[0 0 0 0],&arr=   (*[4]int32)0xc000014280
	//arr[0]=(int32)0,&arr[0]=(*int32)0xc000014280
	//arr[1]=(int32)0,&arr[1]=(*int32)0xc000014284
	//arr[2]=(int32)0,&arr[2]=(*int32)0xc000014288
	//arr[3]=(int32)0,&arr[3]=(*int32)0xc00001428c
	// 数组元素的内存地址是连续的

	fmt.Printf("arr   =(%T)%v,&arr=   (%T)%p\n", arr, arr, &arr, &arr)
	fmt.Printf("arr[0]=(%T)%v,&arr[0]=(%T)%p\n", arr[0], arr[0], &arr[0], &arr[0])
	fmt.Printf("arr[1]=(%T)%v,&arr[1]=(%T)%p\n", arr[1], arr[1], &arr[1], &arr[1])
	fmt.Printf("arr[2]=(%T)%v,&arr[2]=(%T)%p\n", arr[2], arr[2], &arr[2], &arr[2])
	fmt.Printf("arr[3]=(%T)%v,&arr[3]=(%T)%p\n", arr[3], arr[3], &arr[3], &arr[3])
}

func TestArray2(t *testing.T) {
	// 四种初始化数据的方式
	var numArr01 [3]int = [3]int{1, 2, 3}
	fmt.Printf("numArr01=(%T)%v\n", numArr01, numArr01)

	var numArr02 = [3]int{4, 5, 6}
	fmt.Printf("numArr02=(%T)%v\n", numArr02, numArr02)

	var numArr03 = [...]int{7, 8, 9}
	fmt.Printf("numArr03=(%T)%v\n", numArr03, numArr03)

	var numArr04 = [...]int{1: 800, 0: 900, 2: 999}
	fmt.Printf("numArr04=(%T)%v\n", numArr04, numArr04)

	var strArr05 = [...]string{1: "tom", 0: "jack", 2: "mary"}
	fmt.Printf("strArr05=(%T)%v\n", strArr05, strArr05)
}

func TestArray3(t *testing.T) {
	var heroes [3]string = [3]string{"宋江", "吴用", "卢俊义"}
	//fmt.Printf("heroes=%v\n", heroes)
	for i, v := range heroes {
		fmt.Printf("heroes[%v]=(%T)%v\n", i, v, v)
	}
}

func TestArray4(t *testing.T) {
	var arr [3]int = [3]int{11, 22, 33}

	fmt.Printf("arr=(%T)%v\n", arr, arr)
	valueUpdate(arr) // 值拷贝
	fmt.Printf("arr=(%T)%v\n", arr, arr)

	pointerUpdate(&arr) // 地址拷贝
	fmt.Printf("arr=(%T)%v\n", arr, arr)
}

func valueUpdate(arr [3]int) {
	arr[0] = 88 // 修改值拷贝的值，外部不可见
}

func pointerUpdate(arr *[3]int) {
	(*arr)[0] = 99 // 修改地址拷贝指向的值，外部可见
}

func TestArray5(t *testing.T) {
	// 定义/声明二维数组，以及默认初始化
	var arr [4][6]int
	fmt.Printf("arr=(%T)%v\n", arr, arr)
	// 显示初始化
	arr[1][2] = 1
	arr[2][1] = 2
	arr[2][3] = 3

	// 遍历二维数组
	for i := 0; i < len(arr); i ++ {
		for j := 0; j < len(arr[i]); j++ {
			fmt.Printf("%v", arr[i][j])
		}
		fmt.Println()
	}
	fmt.Println("-----------")

	// for-range来遍历二维数组
	for _, v := range arr {
		for _, v1 := range v {
			fmt.Printf("%v", v1)
		}
		fmt.Println()
	}
}

func TestArray6(t *testing.T) {
	var arr [2][3]int

	//&arr      =(*[2][3]int)0xc0000da000
	//&arr[0]   =(*[3]int)0xc0000da000
	//&arr[1]   =(*[3]int)0xc0000da018
	//&arr[0][0]=(*int)0xc0000da000
	//&arr[1][0]=(*int)0xc0000da018

	fmt.Printf("&arr      =(%T)%p\n", &arr, &arr)
	fmt.Printf("&arr[0]   =(%T)%p\n", &arr[0], &arr[0])
	fmt.Printf("&arr[1]   =(%T)%p\n", &arr[1], &arr[1])
	fmt.Printf("&arr[0][0]=(%T)%p\n", &arr[0][0], &arr[0][0])
	fmt.Printf("&arr[1][0]=(%T)%p\n", &arr[1][0], &arr[1][0])
}
